package com.pratap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pratap.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	JdbcTemplate template;
	
	
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;
	
	// Using NamedParameterJdbcTemplate
	public void save1(int id, String pname, double price) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pid", id);
		paramMap.put("pname", pname);
		paramMap.put("price", price);
		
		namedTemplate.update("INSERT INTO PRODUCT VALUES(:pid, :pname, :price)", paramMap);
	}
	
	public Product saveProduct1(Product p) {
		namedTemplate.update("INSERT INTO PRODUCT VALUES(:pid, :pname, :price)", 
				new BeanPropertySqlParameterSource(p));
		return p;
	}
	
	public Product findById1(int pid) {	
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("pid", pid);
		
		return namedTemplate.query("SELECT * FROM PRODUCT WHERE PID= :pid", paramMap, 
				new ResultSetExtractor<Product>() {
					public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
						Product p = new Product();
						if(rs.next()) {
							p.setPid(rs.getInt(1));
							p.setPname(rs.getString(2));
							p.setPrice(rs.getDouble(3));
						}
						return p;
					}
					
				});
	}
	
	
	public Product findAll1() {	
		return null;
	}
	
	public List<Product> findProductByPriceRange1(double low, double high){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("low", low);
		paramMap.put("high", high);
		
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		
		return namedTemplate.query("SELECT * FROM PRODUCT WHERE PRICE BETWEEN :low AND :high", 
									paramSource, 
									new RowMapper<Product>() {
										public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
											Product p = new Product();
											p.setPid(rs.getInt(1));
											p.setPname(rs.getString(2));
											p.setPrice(rs.getDouble(3));
											return p;
										}
									});
	}
	
	
	
	
	
	
	// Using JdbcTemplate
	public void save(int id, String pname, double price) {
		template.update("INSERT INTO PRODUCT VALUES(?, ?, ?)", 
					new PreparedStatementSetter() {
						public void setValues(PreparedStatement ps) throws SQLException {
							// set the data to the ps
							ps.setInt(1, id);
							ps.setString(2, pname);
							ps.setDouble(3, price);
						}
					});
	}

	
	public Product saveProduct(Product p) {
		template.update("INSERT INTO PRODUCT VALUES(?, ?, ?)", 
										p.getPid(), p.getPname(), p.getPrice());
		return p;
	}

	public void updateProductName(int pid, String pname) {
		template.update("UPDATE PRODUCT SET PNAME = ? WHERE PID = ?", 
				new Object[] {pname, pid},  new int[] {Types.VARCHAR, Types.INTEGER});
		

	}

	public void updateProductPrice(int pid, double price) {
		template.update("UPDATE PRODUCT SET PRICE = ? WHERE PID = ?", 
				new Object[] {price, pid},  new int[] {Types.DOUBLE, Types.INTEGER});

	}

	public void deleteById(int pid) {
		template.update("DELETE FROM PRODUCT WHERE PID = "+pid);
	}


	@Override
	public void delete(Product p) {
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement ps = con.prepareStatement("DELETE FROM PRODUCT WHERE PID = ?");
				ps.setInt(1, p.getPid());
				return ps;
				
			}
		});
		
	}


	@Override
	public Product findById(int pid) {	
		return template.query("SELECT * FROM PRODUCT WHERE PID="+pid, 
				new ResultSetExtractor<Product>() {
					public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
						Product p = new Product();
						while(rs.next()) {
							p.setPid(rs.getInt(1));
							p.setPname(rs.getString(2));
							p.setPrice(rs.getDouble(3));
						}
						return p;
					}
				});
	}


	@Override
	public List<Product> findAll() {
		
		return template.query("SELECT * FROM PRODUCT", 
				new RowMapper<Product>() {
					public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
						Product p = new Product();
						
						p.setPid(rs.getInt(1));
						p.setPname(rs.getString(2));
						p.setPrice(rs.getDouble(3));
						
						return p;
					}
				});
	}


	@Override
	public List<Product> findAllProduct() {
		AllProductCallbackHandler rch = new AllProductCallbackHandler();
		template.query("SELECT * FROM PRODUCT", rch);
		
		return rch.getProducts();
	}


	@Override
	public List<Product> findProductByPriceRange(double low, double high) {
		
		List<Product> res = new ArrayList<>();
		
		template.execute(new ConnectionCallback<Void>() {
			public Void doInConnection(Connection con) throws SQLException, DataAccessException {
				
				PreparedStatement ps = 
						con.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?");
				ps.setDouble(1, low);
				ps.setDouble(2, high);
				
				ResultSet rs = ps.executeQuery();
				
				
				while(rs.next()) {
					res.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
				}
				
				return null;
			}
		});
		
		
		return res;
	}


	@Override
	public void saveAll(List<Product> products) {
		template.batchUpdate("INSERT INTO PRODUCT VALUES(?, ?, ?)", 
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Product p = products.get(i);
						ps.setInt(1, p.getPid());
						ps.setString(2, p.getPname());
						ps.setDouble(3, p.getPrice());
					}
					public int getBatchSize() {
						return products.size();
					}
				});
		
	}


	@Override
	public Number saveAndReturnPid(String pname, double price) {
		KeyHolder kh = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("INSERT INTO PRODUCT(PNAME, PRICE) VALUES(?, ?)");
				ps.setString(1, pname);
				ps.setDouble(2, price);
				return ps;
			}
		}, kh);
		
		return kh.getKey();
	}

	
}
