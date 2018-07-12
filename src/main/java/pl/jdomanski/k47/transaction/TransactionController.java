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
    public String transactionAdd(@RequestParam(required = false, defaultValue = "-1") int id, 
                    Model model){
        
        Transaction transaction = transactionService.findById(id);
        
        if (transaction == null){
            transaction = new Transaction();
            transaction.setDate(LocalDate.now());
        } else {
            // editing existing transaction - convert amount
            transaction.setAmount(transaction.getAmount() / 100);
        }

        log.info("Transaction is {}", transaction);
        model.addAttribute(AttributeNames.TRANSACTION, transaction);
        
        return ViewNames.TRANSACTION_FORM;
    }
    
    @PostMapping(Mappings.TRANSACTION_ADD)
    public String processTransaction(@Valid Transaction transaction, 
    		 BindingResult result, RedirectAttributes redirectAttributes, Model model) {

    	if (result.hasErrors()) {
    	    log.error(result.getFieldError("date").toString());

    	    model.addAttribute(AttributeNames.TRANSACTION, transaction);
    		return ViewNames.TRANSACTION_FORM;
    	}
    	
    	log.info("Saving transaction: {}", transaction);
    	
    	transactionService.save(transaction);
    	String message = "Utworzono nową transakcje.";
    	if (transaction.getId() != 0){
    	    message = "Zaktualizowano transakcje.";
    	}
    	redirectAttributes.addFlashAttribute(AttributeNames.MESSAGE, message);
    	
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
