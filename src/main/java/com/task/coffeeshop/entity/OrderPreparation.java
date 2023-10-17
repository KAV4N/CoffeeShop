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
@Table(name = "order_preparation")
public class OrderPreparation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "order_id")
	private Order orderId;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}
