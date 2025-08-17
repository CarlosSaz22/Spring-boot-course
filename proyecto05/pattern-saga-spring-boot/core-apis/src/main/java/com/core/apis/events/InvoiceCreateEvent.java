package com.core.apis.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceCreateEvent {
    private String paymentId;
    private String orderId;
}
