package com.task.coffeeshop.services.impl;

import com.task.coffeeshop.entity.DrinkSize;
import com.task.coffeeshop.repository.DrinkSizeRepository;
import com.task.coffeeshop.services.DrinkSizeService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class DrinkSizeServiceImpl implements DrinkSizeService {
	private final DrinkSizeRepository drinkSizeRepository;

	/**
	 * Finds the drink size by its name.
	 *
	 * @param  drinkSizeName  the name of the drink size to find
	 * @return                the drink size with the given name
	 */
	@Override
	public DrinkSize findDrinkSizebySize(String drinkSizeName){
		List<DrinkSize> allDrinkSizes = drinkSizeRepository.findAll();
		return allDrinkSizes.stream()
				.filter(drinkSize -> drinkSize.getDrinkSize().equals(drinkSizeName))
				.findFirst()
				.orElseThrow(()-> new ResourceNotFoundException("Drink size " + drinkSizeName + " not found"));
	}
}
