package com.spring.cqrs.command.events;

import com.spring.cqrs.command.data.Producto;
import com.spring.cqrs.command.repository.ProductoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoEventsHandle {

    @Autowired
    private ProductoRepository productoRepository;

    @EventHandler
    public void on(ProductoCreateEvent productoCreateEvent) {
        Producto producto = new Producto();
        BeanUtils.copyProperties(productoCreateEvent, producto);
        productoRepository.save(producto);
    }
}
