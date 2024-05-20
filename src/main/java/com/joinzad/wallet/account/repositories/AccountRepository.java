package com.joinzad.wallet.account.repositories;

import com.joinzad.wallet.account.models.Account;
import com.joinzad.wallet.account.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a.balance FROM Account a WHERE a.userId = :userId")
    List<Double> findBalancesByUserId(@Param("userId") String userId);

    Account findByUserIdAndCurrency(String userId, Currency currency);
}
