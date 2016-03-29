package io.pivotal.springdata.pcf.gemfire.data;

import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Component
public class CustomerService implements ServiceInterface<Customer>{

    @Autowired GemfireTemplate customerTemplate;

    public List<Customer> findAll() {
        return customerTemplate.getRegion().entrySet().stream().map(p -> (Customer) p.getValue()).collect(Collectors.toList());
    }

    public void save(Customer customer) {
        customerTemplate.getRegion().put(customer.getCustomerId(), customer);
    }

    public Integer count() {
        return customerTemplate.getRegion().keySet().size();
    }

    public Customer findOne(Long id) {
        return (Customer) customerTemplate.getRegion().get(id);
    }
}
