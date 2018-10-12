package com.revature.repository;

import java.util.List;

import org.hibernate.Session;

import com.revature.model.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {
	public List<Employee> getEmployees() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Employee").list();
	}
}
