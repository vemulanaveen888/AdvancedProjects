package com.pratap.dao;

import java.util.List;

import com.pratap.model.Product;

public interface ProductDao {
	public void persist(Product product);
	public List<Product> findAll();
}