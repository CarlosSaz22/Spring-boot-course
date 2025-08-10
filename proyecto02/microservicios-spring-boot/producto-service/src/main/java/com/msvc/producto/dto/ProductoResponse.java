package com.msvc.producto.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ProductoResponse {

    private String id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;

}
