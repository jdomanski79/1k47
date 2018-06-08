package pl.jdomanski.k47.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.Category;
import pl.jdomanski.k47.category.CategoryType;
import pl.jdomanski.k47.category.CategoryRepository;
import static java.time.LocalDate.now;

@Controller
public class TransactionController {

    @Autowired
    TransactionRepository repository;
    
    @Autowired
    CategoryRepository catDAO;

    @RequestMapping("/")
    public String homeController(){
        
        // testing stuff - to be deleted in later dev
        Transaction transaction = new Transaction();
        Category category = new Category();

        transaction.setDescription("Dane");
        transaction.setDate(now());
        repository.save(transaction);
        
        category.setName("jedzenie");
        category.setType(CategoryType.OUTCOME);
        catDAO.save(category);
        

        return "home";

    }
}
