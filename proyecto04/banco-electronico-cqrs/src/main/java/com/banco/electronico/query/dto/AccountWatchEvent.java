package com.banco.electronico.query.dto;

import com.banco.electronico.commonapi.enums.TransactionType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountWatchEvent {
    private Instant instant;
    private String accountId;
    private double CurrentBalance;
    private TransactionType type;
    private double transactionAmount;
}
