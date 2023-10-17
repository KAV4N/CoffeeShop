package com.task.coffeeshop.services;

import com.task.coffeeshop.entity.DrinkType;

public interface DrinkTypeService {
	DrinkType findDrinkTypeByType(String drinkTypeName);
}
