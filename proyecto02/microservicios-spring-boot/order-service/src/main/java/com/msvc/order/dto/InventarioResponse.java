package com.msvc.order.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioResponse {

    private String codigoSku;
    private boolean isInStock;

}
