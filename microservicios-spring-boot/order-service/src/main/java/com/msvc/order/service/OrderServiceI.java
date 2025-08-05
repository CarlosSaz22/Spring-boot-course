package com.msvc.order.service;

import com.msvc.order.dto.OrderRequest;

public interface OrderServiceI {
    void placeOrder(OrderRequest orderRequest);
}
