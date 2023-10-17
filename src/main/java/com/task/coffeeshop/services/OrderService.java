package com.task.coffeeshop.services;

import com.task.coffeeshop.dto.OrderDto;

public interface OrderService {
	OrderDto createOrderReactive(OrderDto orderDto);

	boolean cancelOrderWithIdReactive(long orderId);
}
