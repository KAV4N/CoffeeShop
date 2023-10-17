package com.task.coffeeshop.services.impl;


import com.task.coffeeshop.dto.OrderPreparationDto;
import com.task.coffeeshop.entity.Order;
import com.task.coffeeshop.entity.OrderPreparation;
import com.task.coffeeshop.infrastructure.OrderStatus;
import com.task.coffeeshop.mapper.OrderPreparationMapper;
import com.task.coffeeshop.repository.OrderPreparationRepository;
import com.task.coffeeshop.services.BaristaService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Service
public class BaristaServiceImpl implements BaristaService {
    private final OrderPreparationRepository orderPreparationRepository;
    private final OrderPreparationMapper orderPreparationMapper;

    /**
     * Creates a new order preparation based on the provided order.
     *
     * @param  order  the order for which the preparation is created
     * @return        the ID of the newly created order preparation
     */
    @Override
    public Long createPreparation(Order order) {
        OrderPreparation orderPreparation = new OrderPreparation();
        orderPreparation.setOrderId(order);

        orderPreparation.setStatus(OrderStatus.WAITING);
        var newOrderPrep = orderPreparationRepository.save(orderPreparation);
        return newOrderPrep.getId();
    }

    /**
     * Retrieves all order preparations.
     *
     * @return a list of order preparation DTOs
     */
    @Override
    public List<OrderPreparationDto> findAll() {
        return orderPreparationMapper.toDto(orderPreparationRepository.findAll());
    }

    /**
     * Deletes an order with the given order preparation ID.
     *
     * @param  orderPrepId  the ID of the order preparation to be deleted
     */
    @Override
    public void deleteOrderWithPreId(Long orderPrepId) {
        OrderPreparation orderPreparation = orderPreparationRepository.findById(orderPrepId).orElseThrow(
                () -> new ResourceNotFoundException("Order with id " + orderPrepId + " not found"));
        orderPreparationRepository.deleteById(orderPrepId);
    }

    /**
     * Checks if an order is waiting based on the given orderId.
     *
     * @param  orderId  the ID of the order to check
     * @return          true if the order is waiting, false otherwise
     */
    @Override
    public boolean checkOrderWaiting(Long orderId) {
        OrderPreparation orderPreparation = orderPreparationRepository.findAll().stream()
                .filter(o -> Objects.equals(o.getOrderId().getOrderId(), orderId))
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order with id " + orderId + " not found"));
        return (orderPreparation.getStatus() == OrderStatus.WAITING);
    }

    /**
     * Process an order reactively.
     *
     * @param  order  the order to process
     * @return        a Flux of OrderStatus
     */
    @Override
    public Flux<OrderStatus> processOrderReactive(Order order) {
        Long orderPrepId = createPreparation(order);
        return Flux.fromIterable(EnumSet.allOf(OrderStatus.class))
                .delayElements(Duration.ofSeconds(5))
                .publishOn(Schedulers.boundedElastic())
                .doFinally(signalType -> {
                    if (signalType == SignalType.CANCEL) {
                        deleteOrderWithPreId(orderPrepId);
                    }
                })
                .map(status -> changeOrderStatus(orderPrepId, status))
                .filter(status -> status == OrderStatus.PICKED_UP)
                .map(status -> {
                    deleteOrderWithPreId(orderPrepId);
                    return status;
                });
    }

    /**
     * Updates the status of an order preparation.
     *
     * @param orderPrepId the ID of the order preparation
     * @param status      the new status of the order preparation
     * @return the updated status of the order preparation
     */
    @Override
    public OrderStatus changeOrderStatus(Long orderPrepId, OrderStatus status) {
        OrderPreparation orderPreparation = orderPreparationRepository.findById(orderPrepId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order with id " + orderPrepId + " not found"));
        orderPreparation.setStatus(status);
        orderPreparationRepository.save(orderPreparation);
        return status;
    }

}
