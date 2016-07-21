package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.User;


public class UserTest {
public static void main(String[]args) {
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.shoppingcart");
	context.refresh();
	UserDAO userDAO= (UserDAO) context.getBean("UserDAO");
	User user=(User)context.getBean("UserDAO");
	user.setID("CG120");
    user.setName("");
   
    userDAO.saveOrUpdate(user);
    if(userDAO.get("sdfsf")==null)
    {
    	System.out.println("category does not exist");
    }
    else
    {
    	System.out.println("category exists..the deatils are...");
    	System.out.println();
    }
    
}
}
