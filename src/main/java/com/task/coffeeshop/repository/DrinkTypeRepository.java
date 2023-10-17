package com.task.coffeeshop.repository;

import com.task.coffeeshop.entity.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Long> {
}