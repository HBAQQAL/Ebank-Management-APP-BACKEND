package com.ebank.ebank;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ebank.ebank.entities.BankAccount;
import com.ebank.ebank.entities.CurrentAccount;
import com.ebank.ebank.entities.Customer;
import com.ebank.ebank.entities.SavingAccount;
import com.ebank.ebank.enums.AccountStatus;
import com.ebank.ebank.repositories.AccountOperationRepository;
import com.ebank.ebank.repositories.BankAccountRepository;
import com.ebank.ebank.repositories.CustomerRepository;
import com.ebank.ebank.services.BankAccountServiceImpl;

@SpringBootApplication
public class EbankApplication implements CommandLineRunner {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Autowired
	private AccountOperationRepository accountOperationRepository;
	@Autowired
	private BankAccountServiceImpl bankAccountServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(EbankApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// Stream.of("Ali", "Mohamed", "Ahmed", "Hassan", "Omar").forEach(name -> {
		// Customer c = new Customer();
		// c.setName(name);
		// c.setEmail(name + "@gmail.com");
		// customerRepository.save(c);
		// });
		// customerRepository.findAll().forEach(cust -> {
		// SavingAccount sa = new SavingAccount();
		// sa.setBalance(10000 + Math.random() * 10000);
		// sa.setCustomer(cust);
		// sa.setCreatedAt(new Date());
		// sa.setInterestRate(Math.random() * 100);
		// sa.setStatus(AccountStatus.ACTIVATED);
		// bankAccountRepository.save(sa);
		// });
		// customerRepository.findAll().forEach(cust -> {
		// CurrentAccount ca = new CurrentAccount();
		// ca.setBalance(10000 + Math.random() * 10000);
		// ca.setCustomer(cust);
		// ca.setCreatedAt(new Date());
		// ca.setOverdraft(1000 + Math.random() * 1000);
		// ca.setStatus(AccountStatus.ACTIVATED);
		// bankAccountRepository.save(ca);
		// });
		// bankAccountRepository.findAll().forEach(bankAccount -> {
		// System.out.println(bankAccount.getBalance());
		// });
		// List<Customer> customers = bankAccountServiceImpl.listCustomers();
		// customers.forEach(c -> {
		// System.out.println(c.getName());
		// });
		// List<BankAccount> bankAccounts = bankAccountServiceImpl.listBankAccounts();
		// bankAccounts.forEach(ba -> {
		// System.out.println(ba.getBalance());
		// });

	}

}
