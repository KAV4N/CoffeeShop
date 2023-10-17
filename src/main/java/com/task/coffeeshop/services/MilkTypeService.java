package com.task.coffeeshop.services;

import com.task.coffeeshop.entity.MilkType;

public interface MilkTypeService {
	MilkType findMilkTypeByType(String milkTypeName);
}
