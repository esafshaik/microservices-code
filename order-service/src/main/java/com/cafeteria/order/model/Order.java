package com.cafeteria.order.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@Column(name = "order_type")
	private String orederType;

	@Column(name = "empId")
	private Integer employeeId;

	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

}
