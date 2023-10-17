package com.task.coffeeshop.services;

import com.task.coffeeshop.entity.DrinkSize;

public interface DrinkSizeService {
	DrinkSize findDrinkSizebySize(String drinkSizeName);
}
