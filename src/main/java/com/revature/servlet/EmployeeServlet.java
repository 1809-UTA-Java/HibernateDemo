package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.revature.model.Employee;
import com.revature.repository.EmployeeDao;
import com.revature.util.HibernateUtil;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
	List<Employee> employees;
	EmployeeDao dao = new EmployeeDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		employees = dao.getEmployees();
		resp.setContentType("text/xml");
		ObjectMapper om = new XmlMapper();
		String obj = om.writeValueAsString(employees);
		PrintWriter pw = resp.getWriter();
		pw.print(obj);
		pw.close();
	}
	
	@Override
	public void destroy() {
		HibernateUtil.shutdown();
	}
}
