package com.pratap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pratap.model.Product;
import com.pratap.service.ProductService;

public class App {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);

		
		ProductService productService = context.getBean(ProductService.class);

		

		productService.add(new Product(1, "Mobile"));
		// productService.add(new Product(2, "Laptop"));

		
	}

}
