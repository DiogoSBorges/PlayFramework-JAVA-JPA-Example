package models;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import play.data.validation.Constraints.Required;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name="productSeqcGenerator", sequenceName="product_seqc")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="productSeqcGenerator")
	private Long id;

	@Basic
	@Required
	@Column(name = "name")
	private String name;

	@Basic
	@Required
	@Column(name = "description")
	private String description;

	@Basic
	@Required
	@Column(name = "brand")
	private String brand;
//
//	@OneToMany(mappedBy = "productFk")
//	private Collection<SaleProduct> saleProducts;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

//	@XmlTransient
//	public Collection<SaleProduct> getSaleProducts() {
//		return saleProducts;
//	}
//
//	public void setSaleProducts(Collection<SaleProduct> saleProducts) {
//		this.saleProducts = saleProducts;
//	}

}
