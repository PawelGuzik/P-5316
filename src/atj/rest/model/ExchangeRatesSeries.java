package atj.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesSeries {

	@XmlElement(name="Table")
	private String table;
	@XmlElement(name="Currency")
	private String currency;
	@XmlElement(name="Code")
	private String code;

	@XmlElement(name="Rates")
	Rates rates;

	public ExchangeRatesSeries() {

	}

	public ExchangeRatesSeries(String table, String currency, String code) {
		super();
		this.table = table;
		this.currency = currency;
		this.code = code;

	}

	public String getCode() {
		return code;
	}

	public String getCurrency() {
		return currency;
	}

	public Rates getRates() {
		return rates;
	}

	public String getTable() {
		return table;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
