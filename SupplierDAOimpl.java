package com.niit.shoppingcart.dao;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Supplier;

import antlr.debug.ListenerBase;

@Repository("supplierDAO")
public class SupplierDAOimpl implements SupplierDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public SupplierDAOimpl(SessionFactory sessionFacory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public void saveOrUpdate(Supplier Supplier)
	{
	sessionFactory.getCurrentSession().saveOrUpdate(Supplier);	
		
	}
	@Transactional
	public void delete(String id) {
		Supplier supplierToDelete=new Supplier();
		supplierToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(supplierToDelete);
		
	}
	@Transactional
	public Supplier get(String id) {
		String hql="from Supplier where id="+"*"+id+"*";
		Query query=(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Supplier> listSupplier=(List<Supplier>)query.list();
		if(listSupplier!=null&&! listSupplier.isEmpty() )
		{
			return listSupplier.get(0);
		}
		
		
			return null;
		}

 @Transactional
	public List<Supplier> list() {
		@SuppressWarnings("unchecked")
		List<Supplier> listSupplier=(List<Supplier>)
		sessionFactory.getCurrentSession().createCriteria(Supplier.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listSupplier;
		}
}