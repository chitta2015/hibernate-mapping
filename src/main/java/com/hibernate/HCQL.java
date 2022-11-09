package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HCQL {

	public static void main(String[] args) {
		 Configuration cfg=new Configuration();
         cfg.configure("hibernate.cfg.xml");
         
         
     	StandardServiceRegistryBuilder sb=new StandardServiceRegistryBuilder();
     	sb.applySettings(cfg.getProperties());    	
     	StandardServiceRegistry registry=sb.build();
     	
     	SessionFactory factory=cfg.buildSessionFactory();
     	Session session=factory.openSession();
     	Transaction t=session.beginTransaction();
     	
     	Criteria c=session.createCriteria(Employee.class);
     	List<Employee> employees=c.list(); 
     	
     	for(Employee e:employees)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
     	System.out.println("==============================================================");
     	
     	//limit and offset
     	c=session.createCriteria(Employee.class);
     	c.setMaxResults(2);
     	c.setFirstResult(1);
    	employees=c.list();
    	for(Employee e:employees)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
    	System.out.println("==============================================================");
    	
    	
     	//greater than
    	//select * from employee where employeesalary>60000;
     	c=session.createCriteria(Employee.class);
     	c.add(Restrictions.gt("salary", new Double(60000)));
     	List<Employee> emps=c.list();
     	for(Employee e:emps)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
     	System.out.println("==============================================================");
     	
     	//greater than
     	//select * from employee where employeesalary<60000;
     	c=session.createCriteria(Employee.class);
     	c.add(Restrictions.lt("salary", new Double(60000)));
     	emps=c.list();
     	for(Employee e:emps)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
     	System.out.println("==============================================================");
     	
     	//select * from employee where employeeId=112;
     	c=session.createCriteria(Employee.class);
     	c.add(Restrictions.eq("employeeId", new Long(112)));
     	emps=c.list();
     	for(Employee e:emps)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
     	System.out.println("==============================================================");
     	
     	
     	c=session.createCriteria(Employee.class);
     	c.add(Restrictions.between("employeeId",new Long(111),new Long(113)));
     	emps=c.list();
     	for(Employee e:emps)
     	{
     		System.out.println(e.getEmployeeName()+" "+e.getEmployeeId()+" "+e.getSalary());
     	}
     	System.out.println("==============================================================");

	}

}
