package com.niit.shoppingcart.dao;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Product;

import antlr.debug.ListenerBase;

@Repository("productDAO")
public class ProductDAOimpl implements ProductDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public ProductDAOimpl(SessionFactory sessionFacory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public void saveOrUpdate(Product Product)
	{
	sessionFactory.getCurrentSession().saveOrUpdate(Product);	
		
	}
	@Transactional
	public void delete(String id) {
		Product ProductToDelete=new Product();
		ProductToDelete.setID(id);
		sessionFactory.getCurrentSession().delete(ProductToDelete);
		
	}
	@Transactional
	public Product get(String id) {
		String hql="from Product where id="+"*"+id+"*";
		Query query=(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> listProduct=(List<Product>)query.list();
		if(listProduct!=null&&! listProduct.isEmpty() )
		{
			return listProduct.get(0);
		}
		
		
			return null;
		}

 @Transactional
	public List<Product> list() {
		@SuppressWarnings("unchecked")
		List<Product> listProduct=(List<Product>)
		sessionFactory.getCurrentSession().createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listProduct;
		}
}