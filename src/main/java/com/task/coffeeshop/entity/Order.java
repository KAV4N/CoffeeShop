package com.task.coffeeshop.entity;


import com.task.coffeeshop.infrastructure.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "coffee_orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "coffee_id")
	private DrinkType coffeeType;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private DrinkSize cupSize;

	@ManyToOne
	@JoinColumn(name = "milk_id")
	private MilkType milkType;

	private Boolean onSite;

	private Double price;
}

