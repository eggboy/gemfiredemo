package io.pivotal.springdata.pcf.gemfire.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Jay Lee on 3/28/16.
 */
public class Customer implements Serializable {

    @Id
    private Long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Customer customer = (Customer) o;

        if (customerId != null ?
            !customerId.equals(customer.customerId) :
            customer.customerId != null)
            return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null)
            return false;
        return emailAddress != null ?
            emailAddress.equals(customer.emailAddress) :
            customer.emailAddress == null;

    }

    @Override public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Customer{" +
            "customerId=" + customerId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            '}';
    }
}
