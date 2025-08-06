package com.msvc.order.service.Impl;


import com.msvc.order.dto.InventarioResponse;
import com.msvc.order.dto.OrderLineItemsDto;
import com.msvc.order.dto.OrderRequest;
import com.msvc.order.model.Order;
import com.msvc.order.model.OrderLineItems;
import com.msvc.order.repository.OrderRepository;
import com.msvc.order.service.OrderServiceI;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Transactional(readOnly = true)
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setNumeroPedido(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
        log.info(order.toString());

        List<String> codigoSku = order.getOrderLineItems().stream()
                .map(OrderLineItems::getCodigoSku)
                .collect(Collectors.toList());

        log.info("Codigo Sku: {}", codigoSku);

        InventarioResponse[] inventarioResponseArray = webClientBuilder.build().get()
                .uri("http://inventario-service:8082/api/inventario", uriBuilder -> uriBuilder.queryParam("codigoSku", codigoSku).build())
                .retrieve()
                .bodyToMono(InventarioResponse[].class)
                .block();

        boolean allProductInStock= Arrays.stream(inventarioResponseArray)
                        .allMatch(InventarioResponse::isInStock);

        log.info("allProductInStock: {}", allProductInStock);

        if(allProductInStock){
            orderRepository.save(order);
            return "Pedido ordenado con exito";
        }else{
            throw new IllegalArgumentException("El producto no esta en stock");
        }


/*
        List<String> codigoSku = order.getOrderLineItems().stream()
                        .map(OrderLineItems::getCodigoSku)
                                .collect(Collectors.toList());

        System.out.println("Codigos sku : " + codigoSku);

        Span inventarioServiceLookup = tracer.nextSpan().name("InventarioServiceLookup");

        try(Tracer.SpanInScope isLookup = tracer.withSpan(inventarioServiceLookup.start())){
            inventarioServiceLookup.tag("call","inventario-service");

            InventarioResponse[] inventarioResponseArray = webCliBuilder.build().get()
                    .uri("http://inventario-service/api/inventario",uriBuilder -> uriBuilder.queryParam("codigoSku",codigoSku).build())
                    .retrieve()
                    .bodyToMono(InventarioResponse[].class)
                    .block();

            boolean allProductosInStock = Arrays.stream(inventarioResponseArray)
                    .allMatch(InventarioResponse::isInStock);

            if(allProductosInStock){
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getNumeroPedido()));
                return "Pedido ordenado con exito";
            }
            else{
                throw new IllegalArgumentException("El producto no esta en stock");
            }
        }finally {
            inventarioServiceLookup.end();
        }

 */
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrecio(orderLineItemsDto.getPrecio());
        orderLineItems.setCantidad(orderLineItemsDto.getCantidad());
        orderLineItems.setCodigoSku(orderLineItemsDto.getCodigoSku());
        return orderLineItems;
    }
}
