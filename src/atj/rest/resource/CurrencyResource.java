package atj.rest.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import atj.rest.service.CurrencyService;

@Path("/rates")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class CurrencyResource {

	private CurrencyService currencyService = new CurrencyService();

	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMidRatePlainText(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		String nbpData = currencyService.callNBPService(table, code, topCount, format);
		currencyService.unmarschalAnswer(nbpData);
		currencyService.createAnswer();
		return currencyService.getMidRatePlainTextAnswer();
	}
	
	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getMidRateXML(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		String nbpData = currencyService.callNBPService(table, code, topCount, format);
		currencyService.unmarschalAnswer(nbpData);
		currencyService.createAnswer();
		File response = new File("C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\tempMarsch.xml");
		return currencyService.xmlAnswer;
	}
	
	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMidRateJSON(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		String nbpData = currencyService.callNBPService(table, code, topCount, format);
		currencyService.unmarschalAnswer(nbpData);
		currencyService.createAnswer();
		File response = new File("C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\tempMarsch.xml");
		return convertXMLToJson(currencyService.xmlAnswer);
	}
	
	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_HTML)
	public String getMidRateHTML(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		String nbpData = currencyService.callNBPService(table, code, topCount, format);
		currencyService.unmarschalAnswer(nbpData);
		currencyService.createAnswer();
		File response = new File("C:\\Users\\Administrator\\Desktop\\JavaProjectsForGIT\\ATJ\\ATJ\\P-5316\\tempMarsch.xml");
		return currencyService.getMidRate().toHTMLString();
	}
	
	

	
	public String convertXMLToJson(String convert) {
		String value = null;
		 try
	        {
	            // Create a new XmlMapper to read XML tags
	            XmlMapper xmlMapper = new XmlMapper();
	            
	            //Reading the XML
	            JsonNode jsonNode = xmlMapper.readTree(convert.getBytes());
	            
	            //Create a new ObjectMapper
	            ObjectMapper objectMapper = new ObjectMapper();
	            
	            //Get JSON as a string
	           value = objectMapper.writeValueAsString(jsonNode);
	            
	          
	            

	        } catch (JsonParseException e)
	        {
	            e.printStackTrace();
	        } catch (JsonMappingException e)
	        {
	            e.printStackTrace();
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 return value;
	    }
	
	

}
