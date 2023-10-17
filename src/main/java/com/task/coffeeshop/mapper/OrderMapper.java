package com.task.coffeeshop.mapper;

import com.task.coffeeshop.dto.OrderDto;
import com.task.coffeeshop.dto.OrderPostDto;
import com.task.coffeeshop.entity.DrinkSize;
import com.task.coffeeshop.entity.DrinkType;
import com.task.coffeeshop.entity.MilkType;
import com.task.coffeeshop.entity.Order;
import com.task.coffeeshop.services.DrinkSizeService;
import com.task.coffeeshop.services.DrinkTypeService;
import com.task.coffeeshop.services.MilkTypeService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

	/**
	 * Maps an OrderDto object to an Order object.
	 *
	 * @param  orderDto           the OrderDto object to be mapped
	 * @param  drinkSizeService   the DrinkSizeService object used for mapping cup size
	 * @param  drinkTypeService   the DrinkTypeService object used for mapping coffee type
	 * @param  milkTypeService    the MilkTypeService object used for mapping milk type
	 * @return                    the mapped Order object
	 */
	@Mapping(target="orderId", ignore = true)
	@Mapping(target = "coffeeType", source = "coffeeType", qualifiedByName = "drinkTypeToId")
	@Mapping(target = "cupSize", source = "cupSize", qualifiedByName = "sizeToId")
	@Mapping(target = "milkType", source = "milkType", qualifiedByName = "milkTypeToId")
	@Mapping(target="onSite", source = "onSite")
	@Mapping(target="price", source = "price")
	public abstract Order orderDtoToOrder(OrderDto orderDto,
										  @Context DrinkSizeService drinkSizeService,
										  @Context DrinkTypeService drinkTypeService,
										  @Context MilkTypeService milkTypeService);

	/**
	 * Converts a cup size to the corresponding drink size ID.
	 *
	 * @param  cupSize  the cup size to be converted
	 * @param  drinkSizeService  the DrinkSizeService instance used for conversion
	 * @return  the corresponding drink size ID
	 */
	@Named("sizeToId")
	public DrinkSize sizeToId(String cupSize, @Context DrinkSizeService drinkSizeService) {
		return drinkSizeService.findDrinkSizebySize(cupSize);
	}

	/**
	 * Converts a drink type to its corresponding ID.
	 *
	 * @param  drinkType         the drink type to be converted
	 * @param  drinkTypeService  the drink type service to use for conversion
	 * @return                   the corresponding ID for the given drink type
	 */
	@Named("drinkTypeToId")
	public DrinkType drinkTypeToId(String drinkType, @Context DrinkTypeService drinkTypeService) {
		return drinkTypeService.findDrinkTypeByType(drinkType);
	}

	/**
	 * Converts a milk type to its corresponding ID.
	 *
	 * @param  milkType          the milk type to convert
	 * @param  milkTypeService   the milk type service to use for conversion
	 * @return                   the corresponding ID of the milk type
	 */
	@Named("milkTypeToId")
	public MilkType milkTypeToId(String milkType, @Context MilkTypeService milkTypeService) {
		return milkTypeService.findMilkTypeByType(milkType);
	}

	/**
	 * Maps an Order object to an OrderDto object.
	 *
	 * @param  order	the Order object to be mapped
	 * @return         	the mapped OrderDto object
	 */
	@Mapping(target = "coffeeType", source = "coffeeType.drinkType")
	@Mapping(target = "cupSize", source = "cupSize.drinkSize")
	@Mapping(target = "milkType", source = "milkType.milkType")
	public abstract OrderDto orderToOrderDto(Order order);

}
