package com.task.coffeeshop.repository;


import com.task.coffeeshop.entity.OrderPreparation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPreparationRepository extends JpaRepository<OrderPreparation, Long> {
}
