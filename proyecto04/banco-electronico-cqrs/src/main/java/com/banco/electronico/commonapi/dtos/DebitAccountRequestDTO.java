package com.banco.electronico.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DebitAccountRequestDTO {
    private String accountId;
    private String currency;
    private double amount;
}
