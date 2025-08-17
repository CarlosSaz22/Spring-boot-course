package com.mcsv.order.sagas;

import com.core.apis.commands.CreateInvoiceCommand;
import com.core.apis.commands.CreateShippingCommand;
import com.core.apis.commands.UpdateOrderStatusCommand;
import com.core.apis.events.InvoiceCreateEvent;
import com.core.apis.events.OrderCreatedEvent;
import com.core.apis.events.OrderShippingEvent;
import com.core.apis.events.OrderUpdateEvent;
import com.mcsv.order.aggregates.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Saga
@AllArgsConstructor
public class OrderManagementSaga {
    private transient CommandGateway commandGateway;

    // Constructor sin argumentos requerido por Axon
    public OrderManagementSaga() {}

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        String paymentId = UUID.randomUUID().toString();
        log.info("SAGA INVOCADO");

        //Asociamos la saga
        SagaLifecycle.associateWith("paymentId", paymentId);
        log.info("Order ID : " + orderCreatedEvent.getOrderId());

        //enviamos los comandos
        commandGateway.send(new CreateInvoiceCommand(paymentId, orderCreatedEvent.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(InvoiceCreateEvent  invoiceCreateEvent) {
        String shippingId = UUID.randomUUID().toString();
        log.info("SAGA continuacion");

        //Asociamos la SAGA con shipping
        SagaLifecycle.associateWith("shippingId", shippingId);

        //enviamos los comandos
        commandGateway.send(new CreateShippingCommand(shippingId, invoiceCreateEvent.getOrderId(),invoiceCreateEvent.getPaymentId()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippingEvent orderShippingEvent){
        commandGateway.send(new UpdateOrderStatusCommand(orderShippingEvent.getOrderId(),String.valueOf(OrderStatus.SHIPPED)));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdateEvent orderUpdateEvent){
        SagaLifecycle.end();
    }
}
