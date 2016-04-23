package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class ProductRepository {
	
	@Transactional
	public List<Product> getAll() {
		Query query = JPA.em().createNativeQuery("SELECT * FROM Product", Product.class);
		List<Product> products = new ArrayList<Product>(); 
		products = query.getResultList();
		
		if(null == products){
			products = new ArrayList<Product>();
		}
		
		return products;
	}

	@Transactional
	public Product getById(Long id) {
		return JPA.em().find(Product.class, id);
	}
	
	@Transactional
	public void save(Product product){
		JPA.em().persist(product);
	}
	
	@Transactional
	public void update(Product product){
		JPA.em().merge(product);
	}
	
	@Transactional
	public void delete(Product product){
		JPA.em().remove(product);
	}

}
