package controllers;

import java.util.List;

import models.Person;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PersonRepository;
import repository.ProductRepository;

public class Service extends Controller{
	
	private PersonRepository personRepository = new PersonRepository();

	@Transactional
	public Result listPersonOnXML(){
		List<Person> persons = personRepository.getAll();
		return ok("<message \"status\"=\"OK\"> " +persons.toString()+ "</message>");
	}
	
	@Transactional
	public Result listPersonOnJSON(){
		List<Person> persons = personRepository.getAll();
		return ok(Json.toJson(persons));
	}
}
