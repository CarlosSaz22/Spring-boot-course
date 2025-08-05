package com.msvc.order.controller;

import com.msvc.order.service.Impl.OrderServiceImpl;

import com.msvc.order.service.OrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderServiceI orderService;

    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> realizarPedido(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Oops! Ha ocurrido un error al realizar el pedido");
    }

     */
}
