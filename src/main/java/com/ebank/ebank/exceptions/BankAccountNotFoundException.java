package com.ebank.ebank.exceptions;

public class BankAccountNotFoundException extends RuntimeException {
  public BankAccountNotFoundException(String message) {
    super(message);
  }

}
