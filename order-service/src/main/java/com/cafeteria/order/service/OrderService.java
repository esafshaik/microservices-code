package com.cafeteria.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafeteria.order.dto.OrderRequest;
import com.cafeteria.order.model.Order;
import com.cafeteria.order.model.OrderItem;
import com.cafeteria.order.repository.OrderItemRepository;
import com.cafeteria.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final OrderItemRepository orderItemRepository;

	public Integer createOrder(OrderRequest orderRequest) {
		
		 RestTemplate restTemplate = new RestTemplate();
		 
		 String uri="http://localhost:8082/api/prod/calories";
		 
		 Integer result = null;
		
		if (orderRequest.getOrderType().equalsIgnoreCase("NEW")) {
			
			log.info("createOrder NEW : "+orderRequest.getOrderType());
			
			Order order = Order.builder().orederType(orderRequest.getOrderType())
					.employeeId(orderRequest.getEmployeeId()).orderDate(LocalDate.now())
					.createdBy(orderRequest.getEmployeeId().toString()).createdDate(LocalDateTime.now()).build();

			Order orderNew = orderRepository.save(order);

			for (String prodId : orderRequest.getItems()) {
				OrderItem orderItem = OrderItem.builder()
						.orderId(orderNew.getOrderId())
						.prodId(prodId)
						.build();
				
				orderItemRepository.save(orderItem);
			}
			
		    result = restTemplate.postForEntity(uri, orderRequest.getItems(), Integer.class).getBody();

		} else if (orderRequest.getOrderType().equalsIgnoreCase("OLD")) {
			
			log.info("createOrder OLD : "+orderRequest.getOrderType());

			Order order = orderRepository.findOrderByEmpId(orderRequest.getEmployeeId());
			
			List<OrderItem> orderItem = orderItemRepository.findOrderItemsByorderId(order.getOrderId());
			
			Order oldOrder = Order.builder()
					.orederType(orderRequest.getOrderType())
					.employeeId(order.getEmployeeId())
					.orderDate(LocalDate.now())
					.createdBy(orderRequest.getEmployeeId().toString())
					.createdDate(LocalDateTime.now())
					.updatedBy(orderRequest.getEmployeeId().toString())
					.updatedDate(LocalDateTime.now())
					.build();

			Order newOrder = orderRepository.save(oldOrder);
			
			

			for (OrderItem item : orderItem) {
				OrderItem pldOrderItem = OrderItem.builder()
												.orderId(newOrder.getOrderId())
												.prodId(item.getProdId())
												.build();
				orderItemRepository.save(pldOrderItem);
			}
			
			result = restTemplate.postForEntity(uri, orderRequest.getItems(), Integer.class).getBody();

		} else {
			throw new RuntimeException("Invalid Data!");
		}
		return result;
	}

	public Integer findTotalCalories(OrderRequest orderRequest) {
		return 0;
		
	}

	public void deleteOrder(Integer orderId) {

	}
}
