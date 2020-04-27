package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName",
						Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hib session
		Session currentSession = sessionFactory.getCurrentSession();		
		
		// save the customer to db
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve/read from db using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// retrieve/read from db using primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		theQuery.executeUpdate();

	}

}






