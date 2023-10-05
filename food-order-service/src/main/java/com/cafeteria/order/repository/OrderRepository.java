package com.cafeteria.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cafeteria.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT * FROM employee_order o WHERE o.emp_id = 1 ORDER BY order_date DESC LIMIT 1", nativeQuery = true)
	Order findOrderByEmpId(Integer employeeId);

}
