package com.skgroceries.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skgroceries.model.Product;

@Component
public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Product product = new Product();
		
		int id = resultSet.getInt(1);
		String name = resultSet.getString(2);
		String brand = resultSet.getString(3);
		String category = resultSet.getString(4);
		int quantity = resultSet.getInt(5);
		double price = resultSet.getDouble(6);
		int count = resultSet.getInt(7);
		product.setBrand(brand);
		product.setCategory(category);
		product.setCount(count);
		product.setPrice(price);
		product.setProductId(id);
		product.setProductName(name);
		product.setQuantityInKgs(quantity);

		return product;
	}

}
