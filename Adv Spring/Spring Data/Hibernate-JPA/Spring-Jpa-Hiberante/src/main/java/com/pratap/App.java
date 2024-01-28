package com.pratap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pratap.model.Product;
import com.pratap.service.ProductService;

public class App {

	public static void main(String[] args) {

		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		
		ProductService productService = ctx.getBean(ProductService.class);

		

		productService.add(new Product(1, "Mobile"));
		productService.add(new Product(2, "Laptop"));

		System.out.println("listAll: " + productService.listAll());

		
		ctx.close();

	}

}
