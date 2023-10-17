package com.task.coffeeshop.services.impl;

import com.task.coffeeshop.entity.DrinkType;
import com.task.coffeeshop.repository.DrinkTypeRepository;
import com.task.coffeeshop.services.DrinkTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DrinkTypeServiceImpl implements DrinkTypeService {
	private final DrinkTypeRepository drinkTypeRepository;

	/**
	 * Finds a drink type by its type.
	 *
	 * @param  drinkTypeName  the name of the drink type to find
	 * @return                the found drink type, or throws a ResourceNotFoundException if not found
	 */
	@Override
	public DrinkType findDrinkTypeByType(String drinkTypeName){
		List<DrinkType> allDrinkSizes = drinkTypeRepository.findAll();

		return allDrinkSizes.stream()
				.filter(drinkType -> drinkType.getDrinkType().equals(drinkTypeName))
				.findFirst()
				.orElseThrow(()-> new ResourceNotFoundException("Drink type " + drinkTypeName + " not found"));
	}
}
