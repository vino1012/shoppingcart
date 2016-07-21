package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Supplier;

public class SupplierTest {
public static void main(String[]args) {
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.shoppingcart");
	context.refresh();
	SupplierDAO supplierDAO= (SupplierDAO) context.getBean("SupplierDAO");
	Supplier supplier=(Supplier)context.getBean("Supplier");
	supplier.setID("SG120");
    supplier.setName("");
    supplier.setDescription("");
    supplierDAO.saveOrUpdate(supplier);
    if(supplierDAO.get("sdfsf")==null)
    {
    	System.out.println("Supplier does not exist");
    }
    else
    {
    	System.out.println("Supplier exists..the deatils are...");
    	System.out.println();
    }
    
}
}
