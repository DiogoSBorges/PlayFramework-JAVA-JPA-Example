package controllers;

import java.util.*;

import models.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PersonRepository;
import repository.ProductRepository;
import repository.SaleProductRepository;
import repository.SaleRepository;

public class SaleController extends Controller {

    private SaleRepository saleRepository = new SaleRepository();
    private PersonRepository personRepository = new PersonRepository();
    private ProductRepository productRepository = new ProductRepository();
    private SaleProductRepository saleProductRepository = new SaleProductRepository();

    private List<Product> addProducts = new ArrayList<Product>();

    private Form<SaleDTO> saleDTOForm;

    @Transactional
    public Result list() {
        List<Sale> sales = saleRepository.getAll();
        return ok(views.html.sales.render(sales));
    }

    @Transactional
    public Result newSale() {
        saleDTOForm = Form.form(SaleDTO.class);
        return ok(views.html.newsale.render(saleDTOForm, optionsPersons(), optionsProducts(), productRepository.getAll()));
    }

    @Transactional
    public Result save() {
        saleDTOForm = Form.form(SaleDTO.class).bindFromRequest();

        System.out.println(saleDTOForm.get().getProductsIds());

        if (saleDTOForm.hasErrors()) {
            flash("error", "Problens have been identified in the register.");
            return ok(views.html.newsale.render(saleDTOForm, optionsPersons(), optionsProducts(), productRepository.getAll()));
        }

        SaleDTO saleDTO = saleDTOForm.get();

        if (null == saleDTO.getProductsIds() || saleDTO.getProductsIds().size() == 0) {
            flash("error", "At least one product should be added.");
            return ok(views.html.newsale.render(saleDTOForm, optionsPersons(), optionsProducts(), productRepository.getAll()));
        }

        //Sale
        Sale sale = new Sale();
        sale.setDescription(saleDTO.getDescription());
        sale.setPersonClient(saleDTO.getPersonClient());
        sale.setDate(new Date());
        saleRepository.save(sale);

        //Product Sale
        for (Long idProduct : saleDTO.getProductsIds()) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProductFk(productRepository.getById(idProduct));
            saleProduct.setSaleFk(sale);
            saleProductRepository.save(saleProduct);
        }

        flash("success", "Registration successfully complete.");
        return redirect(routes.SaleController.list());
    }

    @Transactional
    public Result detail(Long id) {
        Form<Sale> saleForm = Form.form(Sale.class).fill(saleRepository.getById(id));
        return ok(views.html.changesale.render(id, saleForm));
        // return ok("eba");
    }

    @Transactional
    public Result remove(Long id) {
        saleRepository.delete(saleRepository.getById(id));
        flash("success", "Sale successfully removed");
        return list();
    }

    @Transactional
    public Result change(Long id) {
        Sale sale = saleRepository.getById(id);

        Form<Sale> saleForm = Form.form(Sale.class).bindFromRequest();


        if (saleForm.hasErrors()) {
            return badRequest(views.html.changesale.render(id, saleForm));
        }

        saleRepository.update(sale);

        flash("success", "Sale  nº:" + saleForm.get().getId() + " successfully changed.");
        return redirect(routes.SaleController.list());
    }


    public Map<String, String> optionsPersons() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Person person : personRepository.getAll()) {
            options.put(person.getId().toString(), person.getNamePerson());
        }
        return options;
    }

    public Map<String, String> optionsProducts() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Product product : productRepository.getAll()) {
            options.put(product.getId().toString(), product.getName());
        }
        return options;
    }

}
