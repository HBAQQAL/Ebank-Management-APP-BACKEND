package com.ebank.ebank.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.ebank.dtos.CustomerDTO;
import com.ebank.ebank.exceptions.CustomerNotFoundException;
import com.ebank.ebank.services.BankAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerController {
  private BankAccountService bs;

  @GetMapping("/customers")
  public List<CustomerDTO> getCustomers() {
    log.info("Getting all customers");
    return bs.listCustomers();
  }

  @GetMapping("/customers/search")
  public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
    return bs.searchCustomers("%" + keyword + "%");
  }

  @GetMapping("/customers/{id}")
  public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
    return bs.getCustomer(customerId);
  }

  @PostMapping("/customers")
  public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
    return bs.saveCustomer(customerDTO);
  }

  @PutMapping("/customers/{customerId}")
  public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
    customerDTO.setId(customerId);
    return bs.updateCustomer(customerDTO);
  }

  @DeleteMapping("/customers/{id}")
  public void deleteCustomer(@PathVariable Long id) {
    bs.deleteCustomer(id);
  }

}
