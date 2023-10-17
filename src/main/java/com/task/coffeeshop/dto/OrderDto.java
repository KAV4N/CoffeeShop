package com.task.coffeeshop.dto;

public record OrderDto(
	Long orderId,
	String coffeeType,
	String cupSize,
	String milkType,
	Boolean onSite,
	Double price

){
}
