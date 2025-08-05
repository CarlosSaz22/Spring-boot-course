package com.msvc.producto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "producto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class Producto {

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;

}
