package com.task.coffeeshop.dto;

public record OrderPostDto(
		String coffeeType,
		String cupSize,
		String milkType,
		Boolean onSite,
		Double price
){
}
