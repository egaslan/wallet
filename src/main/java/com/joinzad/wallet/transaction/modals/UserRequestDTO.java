package com.joinzad.wallet.transaction.modals;

import com.joinzad.wallet.account.models.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String userId;
    private OperationType operationType;
    private Double amount;
    private Currency fromCurrency;
    private Currency toCurrency;


}
