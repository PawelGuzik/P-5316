package atj.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)

public class Rate {

	@XmlElement(name = "Ask")
	private double ask;
	
	@XmlElement(name = "Bid")
	private double bid;
	
	@XmlElement(name = "EffectiveDate")
	private String effectiveDate;
	
	@XmlElement(name = "No")
	private String no;

	public Rate() {

	}

	public Rate(String no, String effectiveDate, double bid, double ask) {
		super();
		this.no = no;
		this.effectiveDate = effectiveDate;
		this.bid = bid;
		this.ask = ask;

	}

	public double getAsk() {
		return ask;
	}

	public double getBid() {
		return bid;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getNo() {
		return no;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
