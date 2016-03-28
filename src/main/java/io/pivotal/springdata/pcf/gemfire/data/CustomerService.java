package io.pivotal.springdata.pcf.gemfire.data;

import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Component
public class CustomerService implements ServiceInterface<Customer>{

    @Autowired GemfireTemplate customerTemplate;

    public List<Customer> findAll() {
        List<Customer> listCustomers = new ArrayList<Customer>();

        Iterator<Map.Entry<Object, Object>> iterator = customerTemplate.getRegion().entrySet().iterator();
        while (iterator.hasNext()) {
            listCustomers.add((Customer) iterator.next().getValue());
        }

        return listCustomers;
    }

    public void save(Customer customer) {
        customerTemplate.put(customer.getCustomerId(), customer);
    }

    public Integer count() {
        return customerTemplate.getRegion().size();
    }

    public Customer findOne(Long id) {
        return customerTemplate.get(id);
    }
}
