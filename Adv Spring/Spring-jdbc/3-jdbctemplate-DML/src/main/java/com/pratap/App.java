package com.pratap;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pratap.model.Product;
import com.pratap.repository.ProductRepository;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);

		ProductRepository repo = context.getBean(ProductRepository.class);
		// repo.save(1, "mobile", 20000.0);
		// repo.saveProduct(new Product(2, "pen", 100.0));
		// repo.updateProductName(2, "pencil");
		// repo.updateProductPrice(2, 50.0);
		// repo.delete(new Product(1));
		// repo.deleteById(2);
		
		List<Product> products = repo.findAll();
		System.out.println(products);
		
		Product product1 = repo.findById(1);
		System.out.println(product1);
		
		
		List<Product> ps = repo.findAllProduct();
		System.out.println(ps);
		
		
		System.out.println("done");
		
	}

}
