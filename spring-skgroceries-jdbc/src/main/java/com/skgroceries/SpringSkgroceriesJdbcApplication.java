package com.skgroceries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skgroceries.model.Product;
import com.skgroceries.service.IProductService;

@SpringBootApplication
public class SpringSkgroceriesJdbcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSkgroceriesJdbcApplication.class, args);
	}
	
	@Autowired
	IProductService service;

	@Override
	public void run(String... args) throws Exception {
		
		
		//System.out.println(service.addProduct(new Product("Raja Gingelly oil", "Raja","Oil",1,180.00,20))); ---> Add product Check OK
		//System.out.println(service.deleteProduct(108)); ---> Delete product Check OK
		//System.out.println(service.updateProduct(107, 250.00)); ---> Update product Check OK
		System.out.println("*****");
		System.out.println(service.getById(106));
		System.out.println("*****");
		List<Product> products = service.getAllProducts();
		for (Product product : products) {
			System.out.println(product);
		}
		System.out.println("*****");
		List<Product> productsByCategory = service.getByCategory("Masala");
		for (Product product : productsByCategory ) {
			System.out.println(product);
		}
		System.out.println("*****");
		List<Product> productsByBrand = service.getByBrand("Aachi");
		for (Product product : productsByBrand) {
			System.out.println(product);
		}
		System.out.println("*****");
		List<Product> productsByName = service.getByNameContaining("Aa");
		for (Product product : productsByName) {
			System.out.println(product);
		}
		System.out.println("*****");
		List<Product> productsByNameAndQuantity = service.getByNameAndQuantity("Aa",1);
		for (Product product :  productsByNameAndQuantity) {
			System.out.println(product);
		}
		System.out.println("*****");
	}

}
