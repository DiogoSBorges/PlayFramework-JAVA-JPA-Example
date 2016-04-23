package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.Person;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class PersonRepository {

	
	public List<Person> getAll() {
		Query query = JPA.em().createNativeQuery("SELECT * FROM Person", Person.class);
		List<Person> persons = new ArrayList<Person>(); 
		persons = query.getResultList();
		
		if(null == persons){
			persons = new ArrayList<Person>();
		}
		
		return persons;
	}

	@Transactional
	public Person getById(Long id) {
		return JPA.em().find(Person.class, id);
	}
	
	@Transactional
	public void save(Person person){
		JPA.em().persist(person);
	}
	
	@Transactional
	public void update(Person person){
		JPA.em().merge(person);
	}
	
	@Transactional
	public void delete(Person person){
		JPA.em().remove(person);
	}

}
