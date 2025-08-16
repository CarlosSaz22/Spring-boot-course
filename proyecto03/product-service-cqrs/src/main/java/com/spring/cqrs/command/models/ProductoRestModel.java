package com.spring.cqrs.command.models;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoRestModel {

    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
}
