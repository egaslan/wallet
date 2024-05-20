package com.joinzad.wallet.transaction.modals;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperationType {
    DEPOSIT("deposit"),
    WITHDRAWAL("withdrawal"),
    EXCHANGE("exchange");
    private final String value;
}
