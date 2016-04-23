package controllers;

import java.util.List;

import models.Person;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PersonRepository;

public class PersonController extends Controller {

	private PersonRepository personRepository = new PersonRepository();
	private Form<Person> personForm;

	@Transactional
	public Result list() {
		List<Person> persons = personRepository.getAll();
		return ok(views.html.persons.render(persons));
	}

	public Result newPerson() {
		personForm = Form.form(Person.class);
		return ok(views.html.newperson.render(personForm));
	}

	@Transactional
	public Result save() {
		Form<Person> form = personForm.bindFromRequest();

		if (form.hasErrors()) {
			flash("error", "Problens have been identified in the register.");
			return ok(views.html.newperson.render(form));
		}

		Person person = form.get();
		personRepository.save(person);

		flash("success", "Registration successfully complete.");
		return redirect(routes.PersonController.list());

		// return ok("HELLO");
	}

	@Transactional
	public Result detail(Long id) {
		Form<Person> personForm = Form.form(Person.class).fill(personRepository.getById(id));
		return ok(views.html.changeperson.render(id, personForm));
		// return ok("eba");
	}

	@Transactional
	public Result remove(Long id) {
		personRepository.delete(personRepository.getById(id));
		flash("success","Person successfully removed");
		return list();
	}

	@Transactional
	public Result change(Long id) {
		Person  person = personRepository.getById(id);
			
		Form<Person> alterarForm = Form.form(Person.class).bindFromRequest();
		
		
		if (alterarForm.hasErrors()) {
			return badRequest(views.html.changeperson.render(id,alterarForm));
		}
		person.setNamePerson(alterarForm.get().getNamePerson());
		person.setAgePerson(alterarForm.get().getAgePerson());
		personRepository.update(person);
		
		flash("success","Person "+ alterarForm.get().getNamePerson() + " successfully changed.");
		return redirect(routes.PersonController.list());
	}

}
