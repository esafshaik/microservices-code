package com.cafeteria.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.product.dto.ProductRequest;
import com.cafeteria.product.dto.ProductResponse;
import com.cafeteria.product.model.Product;
import com.cafeteria.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.build();

		productRepository.save(product);
		log.info("Product {} is saved ", product.getId());

	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		log.info("List of Products : ", products.size());
		return products.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).build();
	}

	public Integer getTotalCalories(List<String> items) {
		// In progress..... 
		return null;
	}
}
