package com.joinzad.wallet.account.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Currency {
    TRY("TRY"),
    USD("USD");

    private final String value;
}
