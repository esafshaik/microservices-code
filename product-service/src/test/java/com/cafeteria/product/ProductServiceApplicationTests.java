package com.cafeteria.product;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cafeteria.product.dto.ProductRequest;
import com.cafeteria.product.dto.ProductResponse;
import com.cafeteria.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProductService productService;

	@Test
	void findAllProducts() throws Exception {
		
		 when(productService.getAllProducts()).thenReturn(listProductResponses());
		 
		 ResultActions response = mockMvc.perform(get("/api/product"));
		 response.andExpect(status().isOk())
         .andDo(print())
         .andExpect(jsonPath("$.size()", is(listProductResponses().size())));
	}

	@Test
	void createProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest))).andExpect(status().isCreated());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder().name("test food one").description("test food one").build();

	}

	private List<ProductResponse> listProductResponses() {
		List<ProductResponse> listOfProductResponses = new ArrayList<>();
		listOfProductResponses.add(ProductResponse.builder().name("food one").description("test food one").build());
		listOfProductResponses.add(ProductResponse.builder().name("food two").description("test food two").build());
		listOfProductResponses.add(ProductResponse.builder().name("food three").description("test food three").build());
		return listOfProductResponses;
	}

}
