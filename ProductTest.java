package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Product;


public class ProductTest {
public static void main(String[]args) {
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.shoppingcart");
	context.refresh();
	ProductDAO productDAO= (ProductDAO) context.getBean("ProductDAO");
	Product product=(Product)context.getBean("ProductDAO");
	product.setID("CG120");
    product.setName("");
    product.setDescription("");
    productDAO.saveOrUpdate(product);
    if(productDAO.get("sdfsf")==null)
    {
    	System.out.println("Product does not exist");
    }
    else
    {
    	System.out.println("Product exists..the deatils are...");
    	System.out.println();
    }
    
}
}
