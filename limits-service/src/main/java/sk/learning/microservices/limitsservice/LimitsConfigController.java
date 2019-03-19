package sk.learning.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigController {
	
	@Autowired
	private Configuration configuration; 
	
	@GetMapping("/limits")
	public LimitConfig retrieveLimitsFromConfig() {
		return new LimitConfig(configuration.getMaximum(),configuration.getMinimum());	
	}
	
	@GetMapping("/fault-tolerance")
	@HystrixCommand(fallbackMethod="fallbackMethodConfig")
	public LimitConfig faultTolerance() {
		throw new RuntimeException("Not Available");
	}
	
	public LimitConfig fallbackMethodConfig() {
		return  new LimitConfig(6,600);	
	}
}