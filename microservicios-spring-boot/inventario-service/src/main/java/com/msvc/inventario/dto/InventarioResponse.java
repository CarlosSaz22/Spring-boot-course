package com.msvc.inventario.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioResponse {

    private String codigoSku;
    private boolean inStock;

}
