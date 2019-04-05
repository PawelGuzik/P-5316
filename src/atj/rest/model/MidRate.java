package atj.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MidRate")
public class MidRate {

	@XmlElement(name = "MidAskBid")
	private String midAskBid;
	
	@XmlElement(name = "MidAsk")
	private String midAsk;

	@XmlElement(name = "MidBid")
	private String midBid;

	@XmlElement(name = "EffectiveDateStart")
	private String effectiveDateStart;

	@XmlElement(name = "EffectiveDateEnd")
	private String effectiveDateEnd;

	@XmlElement(name = "Table")
	private String table;

	@XmlElement(name = "Currency")
	private String currency;

	@XmlElement(name = "Code")
	private String code;
	


	public MidRate() {

	}
	
	public String toString() {
		return  "MidAskBid: " + midAskBid + ",\n"
				+ "MidAsk: " + midAsk + ",\n"
				+"MidBid: " + midBid + ",\n"
				+ "EffectiveDateStart: " + effectiveDateStart + ",\n"
				+ "EffectiveDateEnd: " + effectiveDateEnd + ",\n"
				+ "Table: " + table + ",\n"
				+ "Currency: " + currency + ",\n"
				+ "Code: " + code + ".";
	}
	
	public String toHTMLString() {
		String result;
		result = "<!DOCTYPE html>" + "\n" + 
		"<html>" + "\n" + 
		"	<head>" + "\n" +
		"		<meta charset=\\\"UTF-8\\\">" + "\n" +
		"		<title>MediumRate</title>" + "\n" + 
		"	</head>" + "\n" + 
		"	<body>" + "\n" +
		"		<h1>Medium rate</h1>" +
		"			<p>MidAskBid: " + midAskBid + "</p>" + "\n" +
		"			<p>MidAsk: " + midAsk + "</p>" + "\n" + 
		"			<p>MidBid: " + midBid + "</p>" + "\n" + 
		"			<p>EffectiveDateStart: " + effectiveDateStart + "</p>" + "\n" + 
		"			<p>EffectiveDateEnd: " + effectiveDateEnd + "</p>" + "\n" + 
		"			<p>Table: " + table + "</p>" + "\n" + 
		"			<p>Currency: " + currency + "</p>" + "\n" + 
		"			<p>Code: " + code + "</p>" + "\n" + 
		"	</body>" + "\n" + 
		"</html>";
		return result;
	}

	public MidRate(double midAsk, double midBid, String effectiveDateStart, String effectiveDateEnd, String table,
			String currency, String code) {
		super();
		this.midAsk = String.format("%.4f", midAsk);
		this.midBid = String.format("%.4f", midBid);
		this.effectiveDateStart = effectiveDateStart;
		this.effectiveDateEnd = effectiveDateEnd;
		this.table = table;
		this.currency = currency;
		this.code = code;
		this.midAskBid = String.format("%.4f", (midAsk + midBid)/2.0);
	}

	public String getMidAsk() {
		return midAsk;
	}

	public void setMidAsk(String midAsk) {
		this.midAsk = midAsk;
	}

	public String getMidBid() {
		return midBid;
	}

	public void setMidBid(String midBid) {
		this.midBid = midBid;
	}

	public String getEffectiveDateStart() {
		return effectiveDateStart;
	}

	public void setEffectiveDateStart(String effectiveDateStart) {
		this.effectiveDateStart = effectiveDateStart;
	}

	public String getEffectiveDateEnd() {
		return effectiveDateEnd;
	}

	public void setEffectiveDateEnd(String effectiveDateEnd) {
		this.effectiveDateEnd = effectiveDateEnd;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
