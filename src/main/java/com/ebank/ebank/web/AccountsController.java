package com.ebank.ebank.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.ebank.dtos.BankAccountDTO;
import com.ebank.ebank.dtos.CurrentAccountDTO;
import com.ebank.ebank.dtos.SavingAccountDTO;
import com.ebank.ebank.services.BankAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
@Slf4j
public class AccountsController {
  private BankAccountService bs;

  @GetMapping("/getAll")
  public List<BankAccountDTO> listBankAccounts() {
    return bs.listBankAccounts();
  }

  @PostMapping("/saveCurrentAccount")
  public CurrentAccountDTO saveCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
    return bs.saveCurrentAccountDTO(currentAccountDTO.getBalance(), currentAccountDTO.getCustomerDTO().getId(),
        currentAccountDTO.getOverDraft());
  }

  @PostMapping("/saveSavingAccount")
  public SavingAccountDTO saveSavingAccountDTO(SavingAccountDTO savingAccountDTO) {
    return bs.saveSavingAccountDTO(savingAccountDTO.getBalance(), savingAccountDTO.getCustomerDTO().getId(),
        savingAccountDTO.getInterestRate());
  }

}
