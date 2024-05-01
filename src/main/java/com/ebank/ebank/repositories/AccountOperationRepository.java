package com.ebank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebank.ebank.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {

}
