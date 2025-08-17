package com.core.apis.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreatedEvent {
    private String orderId;
    private String itemType;
    private BigDecimal price;
    private String currency;
    private String orderStatus;
}
