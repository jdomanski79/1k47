package pl.jdomanski.k47.transaction;


import java.time.LocalDate;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.jdomanski.k47.category.Category;
import pl.jdomanski.k47.category.CategoryService;
import pl.jdomanski.k47.util.AttributeNames;
import pl.jdomanski.k47.util.Mappings;
import pl.jdomanski.k47.util.ViewNames;


@Slf4j
@Controller
@RequestMapping
public class TransactionController {

    // == fields ==
    private final TransactionService transactionService;
    private final CategoryService categoryService;
    
    // == constructors ==
    @Autowired
    public TransactionController(TransactionService transactionService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }
    
    // == model attributes == 
    @ModelAttribute("categories")
    public Iterable<Category> getAllCategories(){
    	return categoryService.getAllCategories();
    }
    
    // == handler methods ==
    @GetMapping(Mappings.TRANSACTION_ADD)
    public String transactionAdd(Transaction transaction){
        transaction.setDate(LocalDate.now());
        return ViewNames.TRANSACTION_FORM;

    }
    
    @PostMapping(Mappings.TRANSACTION_ADD)
    public String processTransaction(@Valid Transaction transaction, 
    		 BindingResult result, RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
    		return ViewNames.TRANSACTION_FORM;
    	}
    	log.info("Saving transaction: {}", transaction);
    	transactionService.save(transaction);
    	
    	redirectAttributes.addFlashAttribute(AttributeNames.MESSAGE, "Utworzono nową transakcje");
    	
    	return Mappings.TRANSACTIONS_LIST_REDIRECT;
    }
    
    @GetMapping(Mappings.TRANSACTIONS_LIST)
    public String transactionListGet(Model model){
        
        model.addAttribute("transactions", transactionService.findAll());
        return ViewNames.TRANSACTIONS_LIST;
    }
    
    @GetMapping(Mappings.TRANSACTION_VIEW)
    public String transactionView(@RequestParam int id, Model model){
        
        Transaction transaction = transactionService.findById(id);
        model.addAttribute(AttributeNames.TRANSACTION, transaction);
        log.info("Viewing transaction: {}", transaction);
        
        return ViewNames.TRANSACTION_VIEW;
    }
}
