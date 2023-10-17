package com.task.coffeeshop.mapper;


import com.task.coffeeshop.dto.OrderPreparationDto;
import com.task.coffeeshop.entity.OrderPreparation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public abstract class OrderPreparationMapper {

	/**
	 * Maps an OrderPreparation object to an OrderPreparationDto object.
	 *
	 * @param  orderPreparation  the OrderPreparation object to be mapped
	 * @return                   the mapped OrderPreparationDto object
	 */
	@Mapping(target = "orderDto", source = "orderId")
	@Mapping(target = "status", source = "status")
	public abstract OrderPreparationDto toDto(OrderPreparation orderPreparation);

	/**
	 * Converts a list of OrderPreparation objects to a list of OrderPreparationDto objects.
	 *
	 * @param  orderPreparations  the list of OrderPreparation objects to be converted
	 * @return                   the list of OrderPreparationDto objects
	 */
	public abstract List<OrderPreparationDto> toDto(List<OrderPreparation> orderPreparations);
}
