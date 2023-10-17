package com.task.coffeeshop.repository;

import com.task.coffeeshop.entity.DrinkSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DrinkSizeRepository extends JpaRepository<DrinkSize, Long> {
}