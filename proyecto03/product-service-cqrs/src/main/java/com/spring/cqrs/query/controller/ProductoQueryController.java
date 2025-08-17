package com.spring.cqrs.query.controller;

import com.spring.cqrs.command.models.ProductoRestModel;
import com.spring.cqrs.query.queries.GetProductoQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<ProductoRestModel> listarProductos(){
        GetProductoQuery getProductoQuery = new GetProductoQuery();
        List<ProductoRestModel> productoRestModels= queryGateway.query(getProductoQuery, ResponseTypes.multipleInstancesOf(ProductoRestModel.class)).join();
    return productoRestModels;
    }
}
