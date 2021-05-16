package com.crudapp.topicApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts= service.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index";
		
	}	
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		// @ModelAttribute reads data from form and converts to Model. Pass the object variable for which
		// form is sending data, here it is Product
		
		service.saveProduct(product);
		return "redirect:/";
		
	}
	
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(value = "id") Long id) {
		
		ModelAndView mav = new ModelAndView("edit_product"); // page name edit_product
		Product product = service.getProductById(id); // get the product
		mav.addObject("product", product); //add the product to Model object to send data to edit form
		return mav; // return page name and object to be displayed on page
		
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long id) {
		service.deleteProductById(id);
		
		return "redirect:/";
		
		
	}
}
