package pl.jdomanski.k47.transaction;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.jdomanski.k47.category.Category;
import pl.jdomanski.k47.category.CategoryService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @Autowired
    CategoryService categoryService;
    
    @ModelAttribute("categories")
    public Iterable<Category> getAllCategories(){
    	return categoryService.getAllCategories();
    }
     
    @GetMapping
    public String transactionGet(Transaction transaction){
        // get all categories for transaction form
        return "transaction/transaction.form";

    }
    
    @PostMapping
    public String transactionPost(@Valid Transaction transaction, 
    		 BindingResult result, RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
    		return "/transaction/transaction.form";
    	}
    	
    	//convert amount to int
    	return "/transaction/transaction.form";
    }
}
