package sk.learning.microservices.currencyexchangeservice;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CurrencyExchangeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EntityManager entityManager;
	
	public ExchangeValue findByFromAndTo(String currency_from, String currency_to) {
			return jdbcTemplate.queryForObject("select * from exchange_value where currency_from = ? and currency_to = ?", new Object[] {currency_from, currency_to}, new BeanPropertyRowMapper<ExchangeValue>(ExchangeValue.class));
		}
	
	public ExchangeValue findById(Long id) {
			return entityManager.find(ExchangeValue.class, id);
		}
}
