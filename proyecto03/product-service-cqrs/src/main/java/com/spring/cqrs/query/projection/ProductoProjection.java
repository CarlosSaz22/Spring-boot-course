package com.spring.cqrs.query.projection;

import com.spring.cqrs.command.data.Producto;
import com.spring.cqrs.command.models.ProductoRestModel;
import com.spring.cqrs.command.repository.ProductoRepository;
import com.spring.cqrs.query.queries.GetProductoQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductoProjection {

    private final ProductoRepository productoRepository;

    @QueryHandler
    public List<ProductoRestModel> handle(GetProductoQuery getProductoQuery) {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoRestModel> productoRestModel = productos.stream()
                .map(producto -> ProductoRestModel
                        .builder()
                        .cantidad(producto.getCantidad())
                        .precio(producto.getPrecio())
                        .nombre(producto.getNombre())
                        .build()).collect(Collectors.toList());
        return productoRestModel;
    }
}
