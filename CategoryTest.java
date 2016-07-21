package com.niit.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.CategoryDAOimpl;
import com.niit.shoppingcart.model.Category;

public class CategoryTest {
	@Autowired
    AnnotationConfigApplicationContext context;
public  void doable() {
	context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.shoppingcart");

	CategoryDAO categoryDAO= (CategoryDAO) context.getBean(CategoryDAOimpl.class);
	Category category=(Category)context.getBean(Category.class);
	category.setID("CG120");
    category.setName("");
    category.setDescription("");
    categoryDAO.saveOrUpdate(category);
    if(categoryDAO.get("sdfsf")==null)
    {
    	System.out.println("category does not exist");
    }
    else
    {
    	System.out.println("category exists..the deatils are...");
    	System.out.println();
    }
    
}
public static void main(String[] args) {
	new CategoryTest().doable();
}
}
