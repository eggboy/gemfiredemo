package io.pivotal.springdata.pcf.gemfire.hateoas;

import io.pivotal.springdata.pcf.gemfire.controller.CustomerController;
import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Component
public class CustomerHATEOASAssembler extends
    ResourceAssemblerSupport<Customer, CustomerHATEOASResource> {

    public CustomerHATEOASAssembler() {
        super(CustomerController.class, CustomerHATEOASResource.class);
    }

    @Override public CustomerHATEOASResource toResource(Customer customer) {

        CustomerHATEOASResource resource = createResourceWithId(customer.getCustomerId(), customer);

        resource.add(linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId())).withSelfRel());
        return resource;
    }

    @Override
    protected CustomerHATEOASResource instantiateResource(Customer customer) {
        return new CustomerHATEOASResource(customer.getCustomerId(), customer);
    }
}
