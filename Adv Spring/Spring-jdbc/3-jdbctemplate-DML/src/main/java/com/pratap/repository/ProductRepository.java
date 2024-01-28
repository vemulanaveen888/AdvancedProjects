package com.pratap.repository;

import java.util.List;

import com.pratap.model.Product;

public interface ProductRepository {
	public void save(int id, String pname, double price);
	public Product saveProduct(Product product);
	public void updateProductName(int pid, String pname );
	public void updateProductPrice(int pid, double price);
	public void deleteById(int pid);
	public void delete(Product p);
	
	public Product findById(int pid);
	public List<Product> findAll();
	public List<Product> findAllProduct();
	
	public List<Product> findProductByPriceRange(double low, double high);
	
	public void saveAll(List<Product> products);
	
	public Number saveAndReturnPid(String pname, double price);
}
