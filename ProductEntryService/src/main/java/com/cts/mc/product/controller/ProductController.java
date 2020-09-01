package com.cts.mc.product.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mc.product.exception.ProductException;
import com.cts.mc.product.model.Product;
import com.cts.mc.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController{
	static Logger log = Logger.getLogger(ProductController.class.getName());
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product)throws ProductException {
		log.debug("Adding product to repository "+product.getProductId());
		return productService.addProduct(product);
		
	}
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) throws ProductException{
		log.debug("Updating product to repository "+product.getProductId());
		return productService.updateProduct(product);
		
	}
	@DeleteMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable Long productId) throws ProductException{
		log.debug("Deleting product from repository "+productId);
		return productService.deleteProduct(productId);
		
	}

	@GetMapping("/get/{productId}")
	public Product getProductById(@PathVariable Long productId) throws ProductException{
		log.debug("Fetching product from repository "+productId);
		return productService.getProductById(productId);
	}

}
