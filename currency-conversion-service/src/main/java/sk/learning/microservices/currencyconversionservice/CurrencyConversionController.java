package sk.learning.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy; 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("currency-converter/from/{currency_from}/to/{currency_to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String currency_from, @PathVariable String currency_to, 
			@PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("currency_from", currency_from);
		uriVariables.put("currency_to", currency_to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{currency_from}/to/{currency_to}", CurrencyConversion.class,
				uriVariables);

		CurrencyConversion response = responseEntity.getBody();

		return new CurrencyConversion(response.getId(), currency_from, currency_to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
	@GetMapping("currency-converter-feign/from/{currency_from}/to/{currency_to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable String currency_from, @PathVariable String currency_to, 
			@PathVariable BigDecimal quantity) {

		CurrencyConversion response = proxy.retrieveExchangeValue(currency_from, currency_to);

		logger.info("{}", response);
		
		return new CurrencyConversion(response.getId(), currency_from, currency_to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
}
