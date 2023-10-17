package com.task.coffeeshop.services.impl;

import com.task.coffeeshop.dto.OrderDto;
import com.task.coffeeshop.entity.Order;
import com.task.coffeeshop.mapper.OrderMapper;
import com.task.coffeeshop.repository.OrderRepository;
import com.task.coffeeshop.services.DrinkSizeService;
import com.task.coffeeshop.services.DrinkTypeService;
import com.task.coffeeshop.services.MilkTypeService;
import com.task.coffeeshop.services.OrderService;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;

import java.util.HashMap;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository ordersRepository;
    private final MilkTypeService milkTypeService;
    private final DrinkTypeService drinkTypeService;
    private final DrinkSizeService drinkSizeService;
    private final OrderMapper orderMapper;
    private final BaristaServiceImpl baristaServiceImpl;

    private HashMap<Long, Disposable> ordersList = new HashMap<>();

    public OrderServiceImpl(final OrderRepository ordersRepository, final MilkTypeService milkTypeService, final DrinkTypeService drinkTypeService, final DrinkSizeService drinkSizeService, final OrderMapper orderMapper, final BaristaServiceImpl baristaServiceImpl) {
        this.ordersRepository = ordersRepository;
        this.milkTypeService = milkTypeService;
        this.drinkTypeService = drinkTypeService;
        this.drinkSizeService = drinkSizeService;
        this.orderMapper = orderMapper;
        this.baristaServiceImpl = baristaServiceImpl;
    }

    /**
     * Creates a new order using the provided order DTO.
     *
     * @param  orderDto  the order DTO containing the details of the order
     * @return           the order DTO representing the created order
     */
    @Override
    public OrderDto createOrderReactive(OrderDto orderDto) {

        Order order = orderMapper.orderDtoToOrder(orderDto, drinkSizeService, drinkTypeService, milkTypeService);
        Order savedOrder = ordersRepository.save(order);
        Long orderId = savedOrder.getOrderId();

        Disposable newOrder = baristaServiceImpl.processOrderReactive(savedOrder)
                .subscribe(finished -> {
                    ordersList.remove(orderId);
                    ordersRepository.deleteById(orderId);
                },error -> {System.out.println("Invalid order");});

        ordersList.put(orderId, newOrder);

        return orderMapper.orderToOrderDto(savedOrder);
    }

    /**
     * Cancels an order with the specified ID.
     *
     * @param  orderId  the ID of the order to cancel
     * @return          true if the order was waiting and successfully cancelled, false otherwise
     */
    @Override
    public boolean cancelOrderWithIdReactive(long orderId) {
        boolean isWaiting = baristaServiceImpl.checkOrderWaiting(orderId);
        if (isWaiting) {
            ordersList.get(orderId).dispose();
            ordersList.remove(orderId);
            ordersRepository.deleteById(orderId);
        }
        return isWaiting;
    }
}
