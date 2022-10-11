package com.skgroceries.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.skgroceries.model.Product;
import com.skgroceries.util.ProductMapper;
import com.skgroceries.util.ProductQueries;

@Repository
public class ProductDaoImpl implements IProductDao {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * @param product
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will add a new product to the products table
	 */
	@Override
	public boolean addProduct(Product product) {
		Object[] productArray = {product.getProductName(),product.getBrand(),product.getCategory() ,product.getQuantityInKgs(),product.getPrice(),product.getCount()};
		int result = jdbcTemplate.update(ProductQueries.ADDQUERY,productArray);
		if(result==0) {
			return false;
		}else {
			return true;
		}
		
	}

	/**
	 * @param id
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will delete a product in the products table
	 */
	@Override
	public boolean deleteProduct(int id) {
		int result = jdbcTemplate.update(ProductQueries.DELETEQUERY, id);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * @param id
	 * @param price
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will Update the price of a product in the products table
	 */
	@Override
	public boolean updateProduct(int id, double price) {
		int result = jdbcTemplate.update(ProductQueries.UPDATEQUERY,price, id);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * @param id
	 * @return A Product object 
	 * 
	 * Action: This will retrieve a product record from products table based on the productID provided by the user
	 */
	@Override
	public Product findById(int id) {
		Product result = jdbcTemplate.queryForObject(ProductQueries.QUERYBYID, new ProductMapper(), id);
		return result;
		
	}

	/**
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table
	 */
	@Override
	public List<Product> findAllProducts() {
		List<Product> productList = jdbcTemplate.query(ProductQueries.QUERYGETALL, new ProductMapper());
		return productList;
	}

	/**
	 * @param category
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on the Category provided by the user
	 */
	@Override
	public List<Product> findByCategory(String category) {
		return jdbcTemplate.query(ProductQueries.QUERYBYCAT, new ProductMapper(), category);
	}

	/**
	 * @param brand
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on the Brand provided by the user
	 */
	@Override
	public List<Product> findByBrand(String brand) {
		
		return jdbcTemplate.query(ProductQueries.QUERYBYBRAND, new ProductMapper(), brand);
	}

	/**
	 * @param productName
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on part of product name given by the user 
	 */
	@Override
	public List<Product> findByNameContaining(String productName) {
		
		return jdbcTemplate.query(ProductQueries.QUERYBYNAME, new ProductMapper(), "%" + productName + "%");
	}

	/**
	 * @param productName
	 * @param quantity
	 * @return A List of Product objects
	 * 
	 * Action: This will retrieve all the products from the products table based on productName and quantity provided by the user
	 */
	@Override
	public List<Product> findByNameAndQuantity(String productName, int quantity) {
		return jdbcTemplate.query(ProductQueries.QUERYBYNAMEANDQUANT, new ProductMapper(), "%"+productName+"%",quantity);
	}

}
