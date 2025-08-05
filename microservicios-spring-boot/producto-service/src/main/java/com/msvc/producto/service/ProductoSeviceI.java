package com.msvc.producto.service;

import com.msvc.producto.dto.ProductoRequest;
import com.msvc.producto.dto.ProductoResponse;
import com.msvc.producto.model.Producto;

import java.util.List;

public interface ProductoSeviceI {
    void createProducto(ProductoRequest productoRequest);
    List<ProductoResponse> getAllProductos();
}
