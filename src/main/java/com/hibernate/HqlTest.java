package com.hibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class HqlTest {

	public static void main(String[] args) {
		
		Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        
    	StandardServiceRegistryBuilder sb=new StandardServiceRegistryBuilder();
    	sb.applySettings(cfg.getProperties());    	
    	StandardServiceRegistry registry=sb.build();
    	
    	SessionFactory factory=cfg.buildSessionFactory();
    	Session session=factory.openSession();
    	Transaction t=session.beginTransaction();
    	
    	 Query<Employee> query=session.createQuery("from Employee");//select * from employee;
		 List<Employee> list=query.list();
		 
		 for(Employee emp:list)
		 {
			 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
		 }
		 
		 System.out.println("====================================================================");
		 NativeQuery<Employee> sql=(NativeQuery<Employee>) session.createNativeQuery("select * from employee select * from employee ");
		 list=query.getResultList();
		 
		 for(Employee emp:list)
		 {
			 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
		 }
		 
		 System.out.println("====================================================================");
		
		 SQLQuery<Employee> sqlquery = session.createSQLQuery("select * from employee ");
		 sqlquery.addEntity(Employee.class);
		 List results = query.list();
		 for(Employee emp:list)
		 {
			 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
		 }
		 
		 System.out.println("====================================================================");
			
		 query=session.createQuery("from Employee e where e.employeeId = 111");
	     Employee emp=query.getSingleResult(); 
	     System.out.println(emp.getEmployeeName()+" "+emp.getEmployeeId());
	     
		 System.out.println("====================================================================");
		 
		 query=session.createQuery("from Employee e order by e.salary desc");
	     list=query.list();
	     System.out.println("Sorted List:");
	     for(Employee e1:list)
	     {
	     		System.out.println(e1.getEmployeeName()+" "+e1.getEmployeeId()+" "+e1.getSalary());
	     }
	     System.out.println("====================================================================");
	     
	     NativeQuery<Employee> maxQuery=session.createNativeQuery("select count(*), empName from Employee");
		 Object  obj=maxQuery.getSingleResult();
         	
		 System.out.println(obj);
	     
	     
		 
	}

}
