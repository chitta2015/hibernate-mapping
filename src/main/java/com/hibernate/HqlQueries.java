package com.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HqlQueries {

	public static void main(String[] args) {
		 Configuration cfg=new Configuration();
         cfg.configure("hibernate.cfg.xml");
         
         
     	StandardServiceRegistryBuilder sb=new StandardServiceRegistryBuilder();
     	sb.applySettings(cfg.getProperties());    	
     	StandardServiceRegistry registry=sb.build();
     	
     	SessionFactory factory=cfg.buildSessionFactory();
     	Session session=factory.openSession();
     	Transaction t=session.beginTransaction();
     	
		/*
		 * Employee e=new Employee(103,"Akash kumar","Delhi","Sr. Developer",75000);
		 */
		/* session.save(e); */
     	
     	//where clause
     	Query<Employee> query=session.createQuery("from Employee e where e.employeeId = 103");
     	List<Employee> list=query.list();
     	
     	for(Employee e1:list)
     	{
     		System.out.println(e1.getEmployeeName()+" "+e1.getEmployeeId());
     	}
     	
     	//order by
     	query=session.createQuery("from Employee e order by e.salary desc");
     	list=query.list();
     	System.out.println("Sorted List:");
     	for(Employee e1:list)
     	{
     		System.out.println(e1.getEmployeeName()+" "+e1.getEmployeeId()+" "+e1.getSalary());
     	}
     	
     	
     	
     	//group by
     	
     	String sql="select sum(e.salary), e.employeeName from Employee e group by e.employeeName";
     	
     	List<?> list1=session.createQuery(sql).list();
     	System.out.println("After group by:");
     	
     	
     	for(int i=0;i<list1.size();i++)
     	{
     		Object obj[]=(Object[]) list1.get(i);
     		System.out.println(obj[0]+" "+obj[1]);
     	}
     	
		//group by
     	
     	sql="select sum(e.salary), e.employeeName from Employee e group by e.employeeName";
     	
     	 query=session.createQuery(sql);
     	 System.out.println("After group by:");
     	 Iterator<?> itr=query.iterate();
     	 while(itr.hasNext())
     	 {
     		Object[] row = (Object[]) itr.next();
     		System.out.println(row[0]+ " "+row[1]);
     	 }
     	
     	
     	//Update 
     	
     	String updateQuery="update Employee set salary = :sal where employeeId = :id";
     	Query uquery=session.createQuery(updateQuery);
     	uquery.setParameter("sal", new Double(90000));
     	uquery.setParameter("id", new Long(113));
     	uquery.executeUpdate();
     	
     	
     	
     	Query q=session.createQuery("select E.employeeName from Employee E");
     	List<Object> rows=q.list();
     	
     	for(Object row:rows)
     	{
     		System.out.println("Name:"+row);
     	}
     	
     	
     	t.commit();
	
	}

}
