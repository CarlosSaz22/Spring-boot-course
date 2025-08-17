package com.mcsv.order.controllers;

import com.mcsv.order.dtos.commands.OrderCreateDTO;
import com.mcsv.order.services.commands.OrderCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderCommandController {
    private final OrderCommandService orderCommandService;

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderCommandService.createOrder(orderCreateDTO);
    }


}
