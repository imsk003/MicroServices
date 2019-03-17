package sk.learning.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {

	@Id
	private Long id;
	
	private String currency_from;
	
	private String currency_to;
	
	private BigDecimal conversionMultiple;
	
	private int port;

	public ExchangeValue() {
	
	}

	public ExchangeValue(Long id, String currency_from, String currency_to, BigDecimal conversionMultiple, int port) {
		this.id = id;
		this.currency_from = currency_from;
		this.currency_to = currency_to;
		this.conversionMultiple = conversionMultiple;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency_from() {
		return currency_from;
	}

	public void setCurrency_from(String currency_from) {
		this.currency_from = currency_from;
	}

	public String getCurrency_to() {
		return currency_to;
	}

	public void setCurrency_to(String currency_to) {
		this.currency_to = currency_to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}	
}
