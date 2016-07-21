
package com.niit.shoppingcart.dao;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Category;

import antlr.debug.ListenerBase;

@Repository("categoryDAO")
public class CategoryDAOimpl implements CategoryDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public CategoryDAOimpl(SessionFactory sessionFacory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public void saveOrUpdate(Category category)
	{
	sessionFactory.getCurrentSession().saveOrUpdate(category);	
		
	}
	@Transactional
	public void delete(String id) {
		Category CategoryToDelete=new Category();
		CategoryToDelete.setID(id);
		sessionFactory.getCurrentSession().delete(CategoryToDelete);
		
	}
	@Transactional
	public Category get(String id) {
		String hql="from category where id="+"*"+id+"*";
		Query query=(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Category> listCategory=(List<Category>)query.list();
		if(listCategory!=null&&! listCategory.isEmpty() )
		{
			return listCategory.get(0);
		}
		
		
			return null;
		}

 @Transactional
	public List<Category> list() {
		@SuppressWarnings("unchecked")
		List<Category> listCategory=(List<Category>)
		sessionFactory.getCurrentSession().createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listCategory;
		}

	
	

	

}
