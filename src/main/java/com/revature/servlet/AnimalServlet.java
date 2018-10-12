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
import com.revature.model.Animal;
import com.revature.repository.AnimalDao;
import com.revature.util.HibernateUtil;

@WebServlet("/animals")
public class AnimalServlet extends HttpServlet {
	List<Animal> animals;
	AnimalDao dao = new AnimalDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//animals = dao.getAnimals();
		resp.setContentType("text/xml");
		ObjectMapper om = new XmlMapper();
		//String obj = om.writeValueAsString(animals);
		String obj = om.writeValueAsString(dao.getAnimalByName("Bobb"));
		PrintWriter pw = resp.getWriter();
		pw.print(obj);
		pw.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new XmlMapper();
		Animal postAnimal = (Animal) om.readValue(req.getInputStream(), Animal.class);
		PrintWriter pw = resp.getWriter();
		pw.print(dao.saveAnimal(postAnimal));
		pw.close();
	}	
	
	@Override
	public void destroy() {
		HibernateUtil.shutdown();
	}
}
