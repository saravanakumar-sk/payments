package sk.poc.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages={"sk.poc.payments"})
public class PaymentsRestAPI extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(PaymentsRestAPI.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(PaymentsRestAPI.class, args);
	}
}
