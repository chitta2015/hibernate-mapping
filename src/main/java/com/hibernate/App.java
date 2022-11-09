package com.hibernate;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.query.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
import org.hibernate.query.NativeQuery;


public class App 
{
    public static void main( String[] args )
    {
    	 Configuration cfg=new Configuration();
         cfg.configure("hibernate.cfg.xml");
         
         
     	StandardServiceRegistryBuilder sb=new StandardServiceRegistryBuilder();
     	sb.applySettings(cfg.getProperties());    	
     	StandardServiceRegistry registry=sb.build();
     	
     	SessionFactory factory=cfg.buildSessionFactory();
     	Session session=factory.openSession();
     	Transaction t=session.beginTransaction();
     	
     	
		
		  Employee e=new Employee(103,"Akash kumar","Delhi","Sr. Developer",75000);
		  
		  session.save(e);
		  
		  //HQL
		 Query query=(Query) session.createQuery("from Employee");//select * from employee;
		 List<Employee> list=query.list();
		 
		 for(Employee emp:list)
		 {
			 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
		 }
		 
		 
		 //Native Query/SQL query
		 
			/*
			 * NativeQuery<Employee>
			 * nativeQuery=session.createSQLQuery("select * from Employee"); List<ResultSet>
			 * list1=(List<ResultSet>) nativeQuery.getResultStream();
			 */
		 
		 NativeQuery<Employee> maxQuery=session.createSQLQuery("select count(empSalary) from Employee");
		 List<Employee> result=maxQuery.getResultList();
         	
		 System.out.println(result.get(0));
		 
		 
		 
		 SQLQuery<Employee> selectQuery=session.createSQLQuery("select * from Employee");
		 selectQuery.addEntity(Employee.class);
		 List<Employee> result1=selectQuery.list();
         	
		 for(Employee emp:result1)
		 {
			 System.out.println(emp.getEmployeeId()+ " "+emp.getEmployeeName()+" "+emp.getCity());
		 }
     	
		/*
		 * Employee emp=session.get(Employee.class, new Long(102)); System.out.printf(
		 * "\n %-5s %-20s  %-10s %-20s %-10s","Id","Name","City","Designation","Salary")
		 * ; System.out.printf(
		 * "\n %-5d %-20s  %-10s %-20s %-10f",emp.getEmployeeId(),emp.getEmployeeName(),
		 * emp.getCity(),emp.getDesignation(),emp.getSalary()) ;
		 */
     	t.commit();
    }
}
