package repository;

import models.SaleProduct;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

/**
 * Created by diogo on 21/04/16.
 */
public class SaleProductRepository {

    @Transactional
    public void save(SaleProduct saleProduct){
        JPA.em().persist(saleProduct);
    }
}
