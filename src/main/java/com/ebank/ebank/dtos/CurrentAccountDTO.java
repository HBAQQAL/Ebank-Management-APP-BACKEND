package com.ebank.ebank.dtos;

import java.util.Date;

import com.ebank.ebank.enums.AccountStatus;

import lombok.Data;

@Data
public class CurrentAccountDTO extends BankAccountDTO {
  private Long id;
  private double balance;
  private Date createdAt;
  private AccountStatus status;
  private CustomerDTO customerDTO;
  private double overDraft;
}
