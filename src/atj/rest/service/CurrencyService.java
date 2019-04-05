package atj.rest.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import atj.rest.model.ExchangeRatesSeries;
import atj.rest.model.MidRate;
import atj.rest.model.Rates;

public class CurrencyService {
	private File tempNbpFile = new File("tempNbpFile.xml");
	private MidRate midRateAnswer;
	private String xmlAnswer;
	private Client client = ClientBuilder.newClient();
	private String answer;
	private ExchangeRatesSeries exchangeRatesSeries = null;

	// konstruowanie adresu URI
//	URI uri = UriBuilder.fromUri("http://localhost:8080/FirstRest/hello").queryParam("idx", "12345").build();
	public void callNBPService(char table, String code, int topCount, String format) {
		URI uri = URI.create("http://api.nbp.pl/");
		// reprezentacja zasobu
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.path("api/exchangerates/rates/").path(table + "/").path(code + "/").path("last/")
				.path(Integer.toString(topCount));
		answer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempNbpFile));
			writer.write(answer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unmarschalAnswer(answer);
		createAnswer();
	}

	private void unmarschalAnswer(String answer) {
		try {
			InputStream inputStream = new FileInputStream(tempNbpFile);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			
			JAXBContext jContext = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller unmarschalerObj = jContext.createUnmarshaller();
			exchangeRatesSeries = (ExchangeRatesSeries) unmarschalerObj.unmarshal(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createAnswer() {
		File tempXmlMidAnswer = new File("tempXmlMidAnswer.xml");
		calculateMidRates();	
		try {
			// creating the JAXB context
			JAXBContext jContext = JAXBContext.newInstance(MidRate.class);
			// creating the marshaller object
			Marshaller marshallObj = jContext.createMarshaller();
			// setting the property to show xml format output
			marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// setting the values in POJO class
			MidRate exchangeXML = midRateAnswer;
			// calling the marshall method
			marshallObj.marshal(exchangeXML, new FileOutputStream(tempXmlMidAnswer));
			StringWriter sw = new StringWriter();
			marshallObj.marshal(exchangeXML, sw);
			xmlAnswer = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void calculateMidRates() {
		Rates ratesSet = exchangeRatesSeries.getRates();
		ratesSet.calculateAskMid();
		ratesSet.calculateBidMid();
		midRateAnswer = new MidRate(ratesSet.getAskMid(), ratesSet.getBidMid(),
				ratesSet.getRateByID(0).getEffectiveDate(),
				ratesSet.getRateByID(ratesSet.getRateList().size() - 1).getEffectiveDate(),
				exchangeRatesSeries.getTable(), exchangeRatesSeries.getCurrency(), exchangeRatesSeries.getCode());
	}

	private String convertXMLToJson(String convert) {
		String value = null;
		try {
			// Create a new XmlMapper to read XML tags
			XmlMapper xmlMapper = new XmlMapper();

			// Reading the XML
			JsonNode jsonNode = xmlMapper.readTree(convert.getBytes());

			// Create a new ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();

			// Get JSON as a string
			value = objectMapper.writeValueAsString(jsonNode);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public String getMidRatePlainTextAnswer() {
		return midRateAnswer.toString();
	}

	public String getMidRateXmlAnswer() {
		return xmlAnswer;
	}

	public String getMidRateJsonAnswer() {
		return convertXMLToJson(xmlAnswer);
	}
	
	public String getMidRateHtmlAnswer() {
		return midRateAnswer.toHTMLString();
	}

}
