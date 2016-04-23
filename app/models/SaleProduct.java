package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sale_product")
public class SaleProduct {


    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "saleProductSeqcGenerator", sequenceName = "sale_product_seqc")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saleProductSeqcGenerator")
    private Long id;

    @JoinColumn(name = "product_fk", referencedColumnName = "id")
    @ManyToOne
    private Product productFk;

    @JoinColumn(name = "sale_fk", referencedColumnName = "id")
    @ManyToOne
    private Sale saleFk;

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductFk() {
        return productFk;
    }

    public void setProductFk(Product productFk) {
        this.productFk = productFk;
    }

    public Sale getSaleFk() {
        return saleFk;
    }

    public void setSaleFk(Sale saleFk) {
        this.saleFk = saleFk;
    }
}
