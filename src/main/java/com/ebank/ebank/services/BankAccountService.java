package com.ebank.ebank.services;

import java.util.Currency;
import java.util.List;

import com.ebank.ebank.dtos.CustomerDTO;
import com.ebank.ebank.entities.BankAccount;
import com.ebank.ebank.entities.CurrentAccount;
import com.ebank.ebank.entities.Customer;
import com.ebank.ebank.entities.SavingAccount;
import com.ebank.ebank.exceptions.CustomerNotFoundException;

public interface BankAccountService {
  CustomerDTO saveCustomer(CustomerDTO customer);

  SavingAccount saveSavingAccount(double initialBalance, Long customerId, double interestRate);

  CurrentAccount saveCurrentAccount(double initialBalance, Long customerId, double overdraft);

  List<CustomerDTO> listCustomers();

  List<BankAccount> listBankAccounts();

  BankAccount getBankAccount(Long accountId);

  void debit(Long accountId, double amount, String description);

  void credit(Long accountId, double amount, String description);

  void transfer(Long sourceAccountId, Long destinationAccountId, double amount);

  CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

  CustomerDTO updateCustomer(CustomerDTO customerDTO);

  void deleteCustomer(Long customerId);

  List<CustomerDTO> searchCustomers(String keyword);

}
