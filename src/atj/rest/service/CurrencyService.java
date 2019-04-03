package atj.rest.service;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import atj.rest.model.ExchangeRatesSeries;
import atj.rest.model.MidRate;
import atj.rest.model.Rates;
import sun.misc.IOUtils;

public class CurrencyService {
	MidRate midRateAnswer;
	public String xmlAnswer;
	Client client = ClientBuilder.newClient();

	// konstruowanie adresu URI
//	URI uri = UriBuilder.fromUri("http://localhost:8080/FirstRest/hello").queryParam("idx", "12345").build();
	public String callNBPService(char table, String code, int topCount, String format) {
		URI uri = URI.create("http://api.nbp.pl/");

		// reprezentacja zasobu
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.path("api/exchangerates/rates/").path(table + "/").path(code + "/").path("last/")
				.path(Integer.toString(topCount));
		String answer;
		// wywoĹ‚anie ĹĽÄ…dania i odebranie odpowiedzi
		// String response =
		// webTarget.request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString();
		// String plainAnswer =
		// webTarget.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		// String xmlAnswer =
		// webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		// String jsonAnswer =
		// webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		// String htmlAnswer =
		// webTarget.request().accept(MediaType.TEXT_HTML).get(String.class);
		// System.out.print(response + "\n" + plainAnswer + "\n" + xmlAnswer + "\n" +
		// htmlAnswer);
		// if(format.equalsIgnoreCase(anotherString))
		answer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		/*if (format != null && format.equalsIgnoreCase("xml")) {
			answer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		} else if (format != null && format.equalsIgnoreCase("json")) {
			answer = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		}*/
		// File tempXML = new
		// File("C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\temp.xml");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\temp.xml"));
			writer.write(answer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	ExchangeRatesSeries exchangeRatesSeries = null;
	public ExchangeRatesSeries unmarschalAnswer(String answer) {
		
		try {
			File tempFile = new File(
					"C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\temp.xml");
			JAXBContext jContext = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller unmarschalerObj = jContext.createUnmarshaller();
			exchangeRatesSeries = (ExchangeRatesSeries) unmarschalerObj.unmarshal(tempFile);
			
			 String poUnmarschalingu = (exchangeRatesSeries.getCode() + " " +
			  exchangeRatesSeries.getCurrency() + exchangeRatesSeries.getTable()+ " " + 
			exchangeRatesSeries.getRates().getRateByID(10).getAsk());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeRatesSeries;
		 
	}
	
	public String getMidRatePlainTextAnswer() {
		return midRateAnswer.toString();
	}
	
	public void createAnswer() {
		File resultFile = new File("C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\tempMarsch.xml");
		Rates ratesSet = exchangeRatesSeries.getRates();
		ratesSet.calculateAskMid();
		ratesSet.calculateBidMid();
		midRateAnswer = new MidRate(
				ratesSet.getAskMid(), 
				ratesSet.getBidMid(), 
				ratesSet.getRateByID(0).getEffectiveDate(), 
				ratesSet.getRateByID(ratesSet.getRateList().size()-1).getEffectiveDate(), 
				exchangeRatesSeries.getTable(), 
				exchangeRatesSeries.getCurrency(), 
				exchangeRatesSeries.getCode());
		
		try{
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(MidRate.class);
		    //creating the marshaller object
		    Marshaller marshallObj = jContext.createMarshaller();
		    //setting the property to show xml format output
		    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    //setting the values in POJO class
		    MidRate exchangeXML = midRateAnswer;
		    //calling the marshall method
		    marshallObj.marshal(exchangeXML, new FileOutputStream(resultFile));
		    
		    StringWriter sw = new StringWriter();
		    marshallObj.marshal(exchangeXML, sw);
		    xmlAnswer = sw.toString();
		} catch(Exception e) {
		    e.printStackTrace();
		}
	
	}
	public MidRate getMidRate() {
		return midRateAnswer;
	}

}
