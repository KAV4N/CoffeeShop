package com.task.coffeeshop.services;

import com.task.coffeeshop.dto.OrderPreparationDto;
import com.task.coffeeshop.entity.Order;
import com.task.coffeeshop.entity.OrderPreparation;
import com.task.coffeeshop.infrastructure.OrderStatus;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import reactor.core.publisher.Flux;

import java.util.List;

public interface BaristaService {
	Long createPreparation(Order order);

	List<OrderPreparationDto> findAll();

	void deleteOrderWithPreId(Long orderPrepId);

	boolean checkOrderWaiting(Long orderId);

	Flux<OrderStatus> processOrderReactive(Order order);


	OrderStatus changeOrderStatus(Long orderPrepId, OrderStatus status);
}
