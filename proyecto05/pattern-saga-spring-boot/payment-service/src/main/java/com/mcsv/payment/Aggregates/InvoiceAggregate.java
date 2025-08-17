package com.mcsv.payment.Aggregates;

import com.core.apis.commands.CreateInvoiceCommand;
import com.core.apis.events.InvoiceCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class InvoiceAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;

    private InvoiceStatus invoiceStatus;

    public InvoiceAggregate() {
    }

    @CommandHandler
    public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand) {
        AggregateLifecycle.apply(new InvoiceCreateEvent(createInvoiceCommand.getPaymentId(), createInvoiceCommand.getOrderId()));
    }
    @EventSourcingHandler
    public void on(InvoiceCreateEvent invoiceCreateEvent) {
        this.paymentId = invoiceCreateEvent.getPaymentId();
        this.orderId = invoiceCreateEvent.getOrderId();
    }


}
