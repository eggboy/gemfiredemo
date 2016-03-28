package io.pivotal.springdata.pcf.gemfire;

import io.pivotal.springdata.pcf.gemfire.data.CustomerService;
import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GemfiredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemfiredemoApplication.class, args);
	}
}

@Component
class GemfireInitialLoadingCLR implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired private CustomerService customerRepository;

	@Override public void run(String... strings) throws Exception {
		log.debug("CommandLineRunner run!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Customer customer1 = new Customer();
		customer1.setFirstName("Jay");
		customer1.setLastName("Lee");
		customer1.setEmailAddress("jaylee@pivotal.io");
		customer1.setCustomerId(new Long(1));

		Customer customer2 = new Customer();
		customer2.setFirstName("Younjin");
		customer2.setLastName("Jeong");
		customer2.setEmailAddress("yjeong@pivotal.io");
		customer2.setCustomerId(new Long(2));

		customerRepository.save(customer1);
		customerRepository.save(customer2);

		log.debug("Region Size :" + customerRepository.count());
	}
}
