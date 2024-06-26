package com.ebank.ebank.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ebank.ebank.dtos.CustomerDTO;
import com.ebank.ebank.entities.Customer;

@Service
public class CustomerMapper {
  public CustomerDTO fromCustomer(Customer customer) {
    CustomerDTO customerDTO = new CustomerDTO();
    BeanUtils.copyProperties(customer, customerDTO);
    return customerDTO;
  }

  public Customer fromCustomerDTO(CustomerDTO customerDTO) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(customerDTO, customer);
    return customer;
  }

}
