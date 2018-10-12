package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Animal;
import com.revature.util.HibernateUtil;

public class AnimalDao {
	public List<Animal> getAnimals() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Animal").list();
	}
	
	public Animal getAnimalByName(String aName) {
		Animal found = null;
		List<Animal> animals = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		
		animals = session.createQuery(
				"from Animal where name = :nameVar")
				.setString("nameVar", aName).list();
		if (!animals.isEmpty()) {
			found = animals.get(0);
		}
		return found;
	}
	
	public int saveAnimal(Animal a) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(a);
		tx.commit();
		return result;
	}
}
