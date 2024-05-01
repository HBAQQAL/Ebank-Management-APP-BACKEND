package com.ebank.ebank.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.ebank.dtos.CustomerDTO;
import com.ebank.ebank.entities.AccountOperation;
import com.ebank.ebank.entities.BankAccount;
import com.ebank.ebank.entities.CurrentAccount;
import com.ebank.ebank.entities.Customer;
import com.ebank.ebank.entities.SavingAccount;
import com.ebank.ebank.enums.AccountStatus;
import com.ebank.ebank.enums.OperationType;
import com.ebank.ebank.exceptions.BalanceNotSufficientException;
import com.ebank.ebank.exceptions.BankAccountNotFoundException;
import com.ebank.ebank.exceptions.CustomerNotFoundException;
import com.ebank.ebank.mappers.CustomerMapper;
import com.ebank.ebank.repositories.AccountOperationRepository;
import com.ebank.ebank.repositories.BankAccountRepository;
import com.ebank.ebank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
  @Autowired
  private BankAccountRepository bankAccountRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AccountOperationRepository accountOperationRepository;
  @Autowired
  private CustomerMapper customerMapper;

  @Override
  public CustomerDTO saveCustomer(CustomerDTO customer) {
    log.info("Saving customer {}", customer.getName());
    Customer c = customerRepository.save(customerMapper.fromCustomerDTO(customer));
    return customerMapper.fromCustomer(c);
  }

  @Override
  public SavingAccount saveSavingAccount(double initialBalance, Long customerId, double interestRate) {
    Customer c = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    SavingAccount sa = new SavingAccount();
    sa.setBalance(initialBalance);
    sa.setCustomer(c);
    sa.setCreatedAt(new Date());
    sa.setInterestRate(Math.random() * 100);
    sa.setStatus(AccountStatus.ACTIVATED);
    bankAccountRepository.save(sa);
    return sa;
  }

  @Override
  public CurrentAccount saveCurrentAccount(double initialBalance, Long customerId, double overdraft) {
    Customer c = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    CurrentAccount ca = new CurrentAccount();
    ca.setBalance(initialBalance);
    ca.setCustomer(c);
    ca.setCreatedAt(new Date());
    ca.setOverdraft(1000 + Math.random() * 1000);
    ca.setStatus(AccountStatus.ACTIVATED);
    bankAccountRepository.save(ca);
    return ca;
  }

  @Override
  public List<CustomerDTO> listCustomers() {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerDTO> customerDTOs = customers.stream().map(c -> customerMapper.fromCustomer(c))
        .collect(Collectors.toList());
    return customerDTOs;
  }

  @Override
  public BankAccount getBankAccount(Long accountId) {
    BankAccount ba = bankAccountRepository.findById(accountId)
        .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    return ba;
  }

  @Override
  public void debit(Long accountId, double amount, String description) {
    BankAccount ba = bankAccountRepository.findById(accountId)
        .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    if (ba.getBalance() < amount) {
      throw new BalanceNotSufficientException("Balance not sufficient");
    }
    AccountOperation ao = new AccountOperation();
    ao.setBankAccount(ba);
    ao.setAmount(amount);
    ao.setOperationDate(new Date());
    ao.setDescription(description);
    ao.setType(OperationType.DEBIT);
    accountOperationRepository.save(ao);

  }

  @Override
  public void credit(Long accountId, double amount, String description) {
    BankAccount ba = bankAccountRepository.findById(accountId)
        .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    AccountOperation ao = new AccountOperation();
    ao.setBankAccount(ba);
    ao.setAmount(amount);
    ao.setOperationDate(new Date());
    ao.setDescription(description);
    ao.setType(OperationType.CREDIT);
    accountOperationRepository.save(ao);
  }

  @Override
  public void transfer(Long sourceAccountId, Long destinationAccountId, double amount) {
    debit(sourceAccountId, amount, "Transfer to account " + destinationAccountId);
    credit(destinationAccountId, amount, "Transfer from account " + sourceAccountId);
  }

  @Override
  public List<BankAccount> listBankAccounts() {
    List<BankAccount> bankAccounts = bankAccountRepository.findAll();
    return bankAccounts;
  }

  @Override
  public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
    Customer c = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    return customerMapper.fromCustomer(c);
  }

  @Override
  public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
    Customer c = customerRepository.findById(customerDTO.getId())
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    c.setName(customerDTO.getName());
    c.setEmail(customerDTO.getEmail());
    customerRepository.save(c);
    return customerDTO;

  }

  @Override
  public void deleteCustomer(Long customerId) {
    Customer c = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    customerRepository.delete(c);
  }

  @Override
  public List<CustomerDTO> searchCustomers(String keyword) {
    List<Customer> customers = customerRepository.searchCustomer(keyword);
    List<CustomerDTO> customerDTOs = customers.stream().map(c -> customerMapper.fromCustomer(c))
        .collect(Collectors.toList());
    return customerDTOs;
  }

}
