package models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import play.data.validation.Constraints.Required;

@Entity
@Table(name="sale")
public class Sale {

	@Id
	@Column(name="id")
	@SequenceGenerator(name="saleSeqcGenerator", sequenceName="sale_seqc")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="saleSeqcGenerator")
	private Long id;
	
	
	@Basic
	@Required
	@Column(name="description")
	private String description;

	@Basic
	@Required
	@Column(name = "date")
	@Type(type="timestamp")
	private Date date;

	@JoinColumn(name="person_client", referencedColumnName="id_person")
	@ManyToOne
	private Person personClient;
	
	@OneToMany(mappedBy="saleFk")
	private Collection<SaleProduct> saleProducts;


	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPersonClient() {
		return personClient;
	}

	public void setPersonClient(Person personClient) {
		this.personClient = personClient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@XmlTransient
	public Collection<SaleProduct> getSaleProducts() {
		return saleProducts;
	}

	public void setSaleProducts(Collection<SaleProduct> saleProducts) {
		this.saleProducts = saleProducts;
	}
	

}
