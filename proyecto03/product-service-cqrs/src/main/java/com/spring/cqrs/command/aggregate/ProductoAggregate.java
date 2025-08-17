package com.spring.cqrs.command.aggregate;

import com.spring.cqrs.command.commands.CreateProductoCommand;
import com.spring.cqrs.command.events.ProductoCreateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Aggregate //le dice a axon que esta entidad debe ser administrada por axon
public class ProductoAggregate {

    @AggregateIdentifier
    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;

    @CommandHandler
    public ProductoAggregate(CreateProductoCommand createProductoCommand) {
        ProductoCreateEvent productoCreateEvent =
                ProductoCreateEvent
                        .builder()
                        .build();

        BeanUtils.copyProperties(createProductoCommand, productoCreateEvent);
        AggregateLifecycle.apply(productoCreateEvent); //registramos eventos
    }

    @EventSourcingHandler //creamos el evento
    public void on(ProductoCreateEvent productoCreateEvent) {
        this.cantidad = productoCreateEvent.getCantidad();
        this.productoId = productoCreateEvent.getProductoId();
        this.nombre = productoCreateEvent.getNombre();
        this.precio = productoCreateEvent.getPrecio();
    }
}
