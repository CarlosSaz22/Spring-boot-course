package com.mcsv.order.services.commands;

import com.core.apis.commands.CreateOrderCommand;
import com.mcsv.order.aggregates.OrderStatus;
import com.mcsv.order.dtos.commands.OrderCreateDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@Service
@AllArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(),orderCreateDTO.getItemType(),orderCreateDTO.getPrice(),
                orderCreateDTO.getCurrency(),String.valueOf(OrderStatus.CREATED)));
    }
}