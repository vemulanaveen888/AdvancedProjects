package com.pratap.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.pratap.model.Product;

public class AllProductCallbackHandler implements RowCallbackHandler {

	private List<Product> products = new ArrayList<>();
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		Product p = new Product();
		
		p.setPid(rs.getInt(1));
		p.setPname(rs.getString(2));
		p.setPrice(rs.getDouble(3));
		
		products.add(p);

	}
	
	public List<Product> getProducts(){
		return products;
	}

}
