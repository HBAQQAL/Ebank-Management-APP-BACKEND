package com.ebank.ebank.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.ebank.dtos.CurrentAccountDTO;
import com.ebank.ebank.dtos.SavingAccountDTO;
import com.ebank.ebank.entities.CurrentAccount;
import com.ebank.ebank.entities.SavingAccount;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountMapper {
  @Autowired
  private CustomerMapper cm;

  public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
    CurrentAccount currentAccount = new CurrentAccount();
    BeanUtils.copyProperties(currentAccountDTO, currentAccount);
    return currentAccount;
  }

  public CurrentAccountDTO toCurrentAccountDTO(CurrentAccount currentAccount) {
    log.info(currentAccount.toString());
    CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
    BeanUtils.copyProperties(currentAccount, currentAccountDTO);
    currentAccountDTO.setCustomerDTO(cm.fromCustomer(currentAccount.getCustomer()));
    currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
    return currentAccountDTO;
  }

  public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO) {
    SavingAccount savingAccount = new SavingAccount();
    BeanUtils.copyProperties(savingAccountDTO, savingAccount);
    return savingAccount;
  }

  public SavingAccountDTO toSavingAccountDTO(SavingAccount savingAccount) {
    log.info("saving Account id " + savingAccount.getId());
    SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
    BeanUtils.copyProperties(savingAccount, savingAccountDTO);
    log.info("saving Account DTO id " + savingAccountDTO.getId());
    savingAccountDTO.setCustomerDTO(cm.fromCustomer(savingAccount.getCustomer()));
    savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
    return savingAccountDTO;
  }

}
