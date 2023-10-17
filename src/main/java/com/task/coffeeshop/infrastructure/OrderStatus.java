package com.task.coffeeshop.infrastructure;

public enum OrderStatus {
	WAITING, IN_PREPARATION, FINISHED, PICKED_UP;
	private static final OrderStatus[] options = values();

	/**
	 * Returns the next order status in the options array.
	 *
	 * @return  the next order status
	 */
	public OrderStatus next() {
		return options[(this.ordinal() + 1) % options.length];
	}
}
