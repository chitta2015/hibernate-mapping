package com.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class NamedQuery {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        
    	StandardServiceRegistryBuilder sb=new StandardServiceRegistryBuilder();
    	sb.applySettings(cfg.getProperties());    	
    	StandardServiceRegistry registry=sb.build();
    	
    	SessionFactory factory=cfg.buildSessionFactory();
    	Session session=factory.openSession();
    	Transaction t=session.beginTransaction();
    	
    	
    	TypedQuery query=session.getNamedQuery("findAllEmployees");
    	
    	List<Employee> employees=query.getResultList();
    	
    	for(Employee emp:employees)
    	{
    		 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
    	}
    	
    	System.out.println("=============================================");
    	TypedQuery query1=session.getNamedQuery("findAllEmployeeNames");  	
    	List<Object> names=query1.getResultList();
    	
    	for(Object name:names)
    	{
    		 System.out.println(name);
    	}
	}

}
