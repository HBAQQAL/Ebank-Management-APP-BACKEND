
package com.ebank.ebank.entities;

import java.util.Date;
import java.util.List;

import com.ebank.ebank.enums.AccountStatus;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
public class BankAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private double balance;
  private Date createdAt;
  @Enumerated(EnumType.STRING)
  private AccountStatus status;
  @ManyToOne
  private Customer customer;
  @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
  private List<AccountOperation> accountOperations;

}
