package com.task.coffeeshop.services.impl;

import com.task.coffeeshop.entity.MilkType;
import com.task.coffeeshop.repository.MilkTypeRepository;
import com.task.coffeeshop.services.MilkTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MilkTypeServiceImpl implements MilkTypeService {

	private final MilkTypeRepository milkTypeRepository;

	/**
	 * Finds a MilkType object by its type.
	 *
	 * @param  milkTypeName  the type of the milk
	 * @return               the MilkType object if found, otherwise throws a ResourceNotFoundException
	 */
	@Override
	public MilkType findMilkTypeByType(String milkTypeName){
		List<MilkType> allDrinkSizes = milkTypeRepository.findAll();
		return allDrinkSizes.stream()
				.filter(milkType -> milkType.getMilkType().equals(milkTypeName))
				.findFirst()
				.orElseThrow(()-> new ResourceNotFoundException("Milk type " + milkTypeName + " not found"));
	}
}
