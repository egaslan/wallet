package com.joinzad.wallet.transaction.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String type;
    private String userId;
    private Double amount;
    private String fromCurrency;
    private String toCurrency;


}
