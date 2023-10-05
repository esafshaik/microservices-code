package com.cafeteria.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cafeteria.order.dto.OrderRequest;
import com.cafeteria.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody OrderRequest orderRequest) {
		orderService.createOrder(orderRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public void findTotalCalories(@RequestBody OrderRequest orderRequest) {
		orderService.findTotalCalories(orderRequest);
	}
	
	@DeleteMapping("/delete/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteOrder(@PathVariable Integer orderId) {
		orderService.deleteOrder(orderId);
	}
}
