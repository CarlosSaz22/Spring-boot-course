package com.mcsv.shipping.Aggregates;

import com.core.apis.commands.CreateShippingCommand;
import com.core.apis.events.InvoiceCreateEvent;
import com.core.apis.events.OrderShippingEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShippingAggregate {
    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    public ShippingAggregate() {
    }

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand) {
        AggregateLifecycle.apply(new OrderShippingEvent(createShippingCommand.getShippingId(), createShippingCommand.getOrderId(),createShippingCommand.getPaymentId()));
    }

    @EventSourcingHandler
    public void on(OrderShippingEvent orderShippingEvent) {
        this.shippingId = orderShippingEvent.getShippingId();
        this.paymentId = orderShippingEvent.getPaymentId();
        this.orderId = orderShippingEvent.getOrderId();
    }
}
