package pl.jdomanski.k47.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.CategoryService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String transactionGet(Model model, Transaction transaction){
        
        
        
        model.addAttribute("categories", categoryService.getAllCategories());
        return "transaction.form";

    }
}
