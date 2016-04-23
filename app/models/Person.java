package models;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlTransient;

import play.data.validation.Constraints.Required;
import repository.PersonRepository;

@Entity
@Table(name="person")
public class Person{
	
	@Id
	@Column(name="id_person")
	@SequenceGenerator(name="personSeqcGenerator", sequenceName="person_seqc")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personSeqcGenerator")
	private Long id;
	
	@Basic
	@Required
	@Column(name="name_person")
	private String namePerson;
	
	@Basic
	@Required
	@Column(name="age_person")
	private Integer agePerson;
	
	@OneToMany(mappedBy="personClient")
	private Collection<Sale> sales;
	
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}

	public Integer getAgePerson() {
		return agePerson;
	}

	public void setAgePerson(Integer agePerson) {
		this.agePerson = agePerson;
	}
	
	@XmlTransient
	public Collection<Sale> getSales() {
		return sales;
	}

	public void setSales(Collection<Sale> sales) {
		this.sales = sales;
	}
	
	@Override
	public String toString(){
		return "Person [id="+id+", name="+namePerson+", age="+agePerson+"]";
	}
	

}
