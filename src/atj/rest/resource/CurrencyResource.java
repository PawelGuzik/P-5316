package atj.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
		currencyService.callNBPService(table, code, topCount, format);
		return currencyService.getMidRatePlainTextAnswer();
	}

	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getMidRateXML(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		currencyService.callNBPService(table, code, topCount, format);
		return currencyService.getMidRateXmlAnswer();
	}

	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMidRateJSON(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		currencyService.callNBPService(table, code, topCount, format);
		return currencyService.getMidRateJsonAnswer();
	}

	@GET
	@Path("/{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_HTML)
	public String getMidRateHTML(@PathParam("table") char table, @PathParam("code") String code,
			@PathParam("topCount") int topCount, @QueryParam("format") String format) {
		currencyService.callNBPService(table, code, topCount, format);
		return currencyService.getMidRateHtmlAnswer();
	}

}
