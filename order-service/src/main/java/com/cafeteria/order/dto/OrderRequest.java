package com.cafeteria.order.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	private Integer employeeId;

	private List<String> items;
	
	private LocalDate orderTo;
	
	private LocalDate orderFrom;

}
