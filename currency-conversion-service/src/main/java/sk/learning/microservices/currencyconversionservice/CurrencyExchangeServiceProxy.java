package sk.learning.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange-service", url="localhost:8000")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("currency-exchange/from/{currency_from}/to/{currency_to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable("currency_from") String currency_from, 
			@PathVariable("currency_to") String currency_to);
}
