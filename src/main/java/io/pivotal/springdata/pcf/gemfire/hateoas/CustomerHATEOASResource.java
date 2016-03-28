package io.pivotal.springdata.pcf.gemfire.hateoas;

import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Jay Lee on 3/28/16.
 */
public class CustomerHATEOASResource extends ResourceSupport {
    private Long customerId;
    private Customer customer;

    public CustomerHATEOASResource(Long customerId, Customer customer) {
        this.customerId = customerId;
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
