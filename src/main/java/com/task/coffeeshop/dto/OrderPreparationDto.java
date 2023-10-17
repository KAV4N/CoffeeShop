package com.task.coffeeshop.dto;

public record OrderPreparationDto(
		OrderDto orderDto,
		String status
) {
}
