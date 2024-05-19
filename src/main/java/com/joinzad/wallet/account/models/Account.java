package com.joinzad.wallet.account.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    private String userId;
    private String userName;
    private Double balance;
    private Currency currency;

}

