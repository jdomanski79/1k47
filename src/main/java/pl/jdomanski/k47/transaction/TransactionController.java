package pl.jdomanski.k47.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.Category;
import pl.jdomanski.k47.category.CategoryRepository;
import pl.jdomanski.k47.category.CategoryType;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository repository;
    
    @Autowired
    CategoryRepository catDAO;

    @GetMapping
    public String transactionGet(Model model, Transaction transaction){
        Category category = new Category();
        category.setName("jedzenie");
        category.setType(CategoryType.OUTCOME);
        model.addAttribute("categories", category);
        return "transaction.form";

    }
}
