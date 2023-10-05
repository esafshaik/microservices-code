package com.cafeteria.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findOrderItemsByorderId(Integer orderId);

}
