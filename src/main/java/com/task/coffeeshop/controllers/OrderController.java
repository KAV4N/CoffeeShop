package com.task.coffeeshop.controllers;

import com.task.coffeeshop.dto.OrderDto;
import com.task.coffeeshop.dto.OrderPostDto;
import com.task.coffeeshop.dto.OrderPreparationDto;
import com.task.coffeeshop.services.BaristaService;
import com.task.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService ordersService;
	@Autowired
	private BaristaService baristaServiceImpl;

	/**
	 * Creates an order using the provided order data.
	 *
	 * @param  orderPostDto  the order data to create the order
	 * @return               the created order details
	 */
	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderPostDto orderPostDto) {
		OrderDto orderDto = new OrderDto(1L,
											orderPostDto.coffeeType(),
											orderPostDto.cupSize(),
											orderPostDto.milkType(),
											orderPostDto.onSite(),
											orderPostDto.price());
		OrderDto createdOrder = ordersService.createOrderReactive(orderDto);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	/**
	 * Retrieves all orders.
	 *
	 * @return a ResponseEntity containing a list of OrderPreparationDto objects
	 */
	@GetMapping
	public ResponseEntity<List<OrderPreparationDto>> getAllOrders() {
		return ResponseEntity.ok(baristaServiceImpl.findAll());
	}

	/**
	 * Cancels an order with the given order ID.
	 *
	 * @param  orderId	the ID of the order to cancel
	 * @return         	a ResponseEntity with a String indicating whether the order was successfully canceled or not
	 */
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
		boolean canceled = ordersService.cancelOrderWithIdReactive(orderId);
		return ResponseEntity.ok(canceled ? "Order canceled" : "Order not found or is already in preparation");
	}

}
