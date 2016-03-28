package io.pivotal.springdata.pcf.gemfire.controller;

import io.pivotal.springdata.pcf.gemfire.data.CustomerService;
import io.pivotal.springdata.pcf.gemfire.domain.Customer;
import io.pivotal.springdata.pcf.gemfire.hateoas.CustomerHATEOASAssembler;
import io.pivotal.springdata.pcf.gemfire.hateoas.CustomerHATEOASResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired CustomerService customerService;
    @Autowired
    private CustomerHATEOASAssembler customerHATEOASAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<CustomerHATEOASResource> getCustomers() {
        List<CustomerHATEOASResource> listResources = new ArrayList<>();

        customerService.findAll().stream().forEach(customer -> listResources.add(customerHATEOASAssembler.toResource(customer)));

        return listResources;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody CustomerHATEOASResource getCustomer(@PathVariable("id") Long id) {
        return customerHATEOASAssembler.toResource(customerService.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Customer putCustomer(@RequestBody Customer customer) {
        LOG.debug("POST ::: " + customer);
        customerService.save(customer);

        return customer;
    }
}
