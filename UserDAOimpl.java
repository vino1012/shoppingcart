package com.niit.shoppingcart.dao;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.User;

import antlr.debug.ListenerBase;

@Repository("userDAO")
public class UserDAOimpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public UserDAOimpl(SessionFactory sessionFacory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public void saveOrUpdate(User User)
	{
	sessionFactory.getCurrentSession().saveOrUpdate(User);	
		
	}
	@Transactional
	public void delete(String id) {
		User UserToDelete=new User();
		UserToDelete.setID(id);
		sessionFactory.getCurrentSession().delete(UserToDelete);
		
	}
	@Transactional
	public User get(String id) {
		String hql="from User where id="+"*"+id+"*";
		Query query=(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<User> listUser=(List<User>)query.list();
		if(listUser!=null&&! listUser.isEmpty() )
		{
			return listUser.get(0);
		}
		
		
			return null;
		}

 @Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser=(List<User>)
		sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
		}
}
