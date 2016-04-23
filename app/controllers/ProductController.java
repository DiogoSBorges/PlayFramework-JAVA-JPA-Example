package controllers;

import java.util.List;

import models.Product;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.ProductRepository;
import scala.util.parsing.json.JSON;

public class ProductController extends Controller {
	
	ProductRepository productRepository = new ProductRepository();
	
	private Form<Product> productForm;

	@Transactional
	public Result list() {
		List<Product> products = productRepository.getAll();
		return ok(views.html.products.render(products));
	}

	public Result newProduct() {
		productForm = Form.form(Product.class);
		return ok(views.html.newproduct.render(productForm));
	}

	@Transactional
	public Result save() {
		Form<Product> form = productForm.bindFromRequest();

		if (form.hasErrors()) {
			flash("erro", "Problens have been identified in the register.");
			return ok(views.html.newproduct.render(form));
		}

		Product product = form.get();
		productRepository.save(product);

		flash("success", "Registration successfully complete.");
		return redirect(routes.ProductController.list());

		// return ok("HELLO");
	}

	@Transactional
	public Result detail(Long id) {
		Product product = productRepository.getById(id);

		if(request().accepts("application/json")){
            return ok(Json.toJson(product));
		}else{
            Form<Product> productForm = Form.form(Product.class).fill(product);
            return ok(views.html.changeproduct.render(id, productForm));
		}
	}

	@Transactional
	public Result remove(Long id) {
		productRepository.delete(productRepository.getById(id));
		flash("success","Product successfully removed");
		return list();
	}

	@Transactional
	public Result change(Long id) {
		Product  product = productRepository.getById(id);
			
		Form<Product> productForm = Form.form(Product.class).bindFromRequest();
		
		
		if (productForm.hasErrors()) {
			return badRequest(views.html.changeproduct.render(id,productForm));
		}
		
		product.setName(productForm.get().getName());
		product.setDescription(productForm.get().getDescription());
		product.setBrand(productForm.get().getBrand());
		
		productRepository.update(product);
		
		flash("success","Product "+ productForm.get().getName() + " successfully changed.");
		return redirect(routes.ProductController.list());
	}

	public Result productJSON(Long idProduct){
		Product product = productRepository.getById(idProduct);
		return ok(Json.toJson(product));
	}

}
