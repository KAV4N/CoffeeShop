package com.task.coffeeshop.repository;

import com.task.coffeeshop.entity.MilkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilkTypeRepository extends JpaRepository<MilkType, Long> {
}