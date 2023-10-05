package com.cafeteria.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${prod.url}")
	private String prod_url;
	
	public Integer createOrder(OrderRequest orderRequest) {
		
		 RestTemplate restTemplate = new RestTemplate();
		 
		 log.info("OrderItemRepository :: createOrder");
			
			Order order = Order.builder()
							  .employeeId(orderRequest.getEmployeeId()).orderDate(LocalDate.now())
					          .createdBy(orderRequest.getEmployeeId().toString())
					          .createdDate(LocalDateTime.now())
					          .build();

			Order orderNew = orderRepository.save(order);

			for (String prodId : orderRequest.getItems()) {
				OrderItem orderItem = OrderItem.builder()
						.orderId(orderNew.getOrderId())
						.prodId(prodId)
						.build();
				
				orderItemRepository.save(orderItem);
			}
			
			return restTemplate.postForEntity(prod_url, orderRequest.getItems(), Integer.class).getBody();

	}

	public void findTotalCalories(OrderRequest orderRequest) {
		log.info("findTotalCalories :: In Progress");
		
	}

	public void deleteOrder(Integer orderId) {
		log.info("deleteOrder :: In Progress");
	}
}
