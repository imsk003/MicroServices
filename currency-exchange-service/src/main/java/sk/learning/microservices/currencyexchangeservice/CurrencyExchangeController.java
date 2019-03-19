package sk.learning.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeDao currencyExchangeDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("currency-exchange/from/{currency_from}/to/{currency_to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String currency_from, @PathVariable String currency_to) {
		ExchangeValue exchangeValue = currencyExchangeDao.findByFromAndTo(currency_from, currency_to);
//		ExchangeValue exchangeValue = currencyExchangeDao.findById(10001L);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}", exchangeValue);
		
		return exchangeValue;
	}
}
