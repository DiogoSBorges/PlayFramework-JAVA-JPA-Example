package models;

import play.data.validation.Constraints;

import java.util.List;

/**
 * Created by diogo on 01/04/16.
 */
public class SaleDTO {
    private Person personClient;

    private List<Long> productsIds;

    private String description;

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

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
