package atj.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Rates {

	@XmlElement(name = "Rate")
	private List<Rate> rate;
	private double askMid;
	private double bidMid;


	public Rates() {

	}
	


	public void calculateAskMid() {
		double sum = 0.0;
		for (Rate rate2 : rate) {
			sum += rate2.getAsk();
		}
		askMid = sum / rate.size();
	}

	public void calculateBidMid() {
		double sum = 0.0;
		for (Rate rate2 : rate) {
			sum += rate2.getBid();
		}
		bidMid = sum / rate.size();
	}

	public double getAskMid() {
		return askMid;
	}

	public void setAskMid(double askMid) {
		this.askMid = askMid;
	}

	public double getBidMid() {
		return bidMid;
	}

	public void setBidMid(double bidMid) {
		this.bidMid = bidMid;
	}

	public Rates(List<Rate> rate) {
		super();
		this.rate = rate;
	}

	public List<Rate> getRateList() {
		return rate;
	}

	public void setRateList(List<Rate> rate) {
		this.rate = rate;
	}

	public Rate getRateByID(int id) {
		if (id < rate.size()) {
			return rate.get(id);

		} else {
			return null;
		}

	}
}
