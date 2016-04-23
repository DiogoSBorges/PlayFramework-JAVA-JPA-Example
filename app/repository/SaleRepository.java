package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.Product;
import models.Sale;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class SaleRepository {

	public List<Sale> getAll() {
		Query query = JPA.em().createNativeQuery("SELECT * FROM Sale", Sale.class);
		List<Sale> sales = new ArrayList<Sale>();
		sales = query.getResultList();

		if (null == sales) {
			sales = new ArrayList<Sale>();
		}

		return sales;
	}

	@Transactional
	public Sale getById(Long id) {
		return JPA.em().find(Sale.class, id);
	}

	@Transactional
	public void save(Sale sale) {
		JPA.em().persist(sale);
	}

	@Transactional
	public void update(Sale sale) {
		JPA.em().merge(sale);
	}

	@Transactional
	public void delete(Sale sale) {
		JPA.em().remove(sale);
	}

}
