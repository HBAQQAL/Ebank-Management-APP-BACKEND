package com.ebank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebank.ebank.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
