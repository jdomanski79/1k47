package pl.jdomanski.k47;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.CategoryType;
import pl.jdomanski.k47.transaction.Transaction;
import pl.jdomanski.k47.transaction.TransactionRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    TransactionRepository rep;
    
    @GetMapping
    public String home(){
        
        List<Object[]> list = rep.findByIncomeGroupByCategory(CategoryType.INCOME);
        System.out.println("Found:");
        for (Object[] o : list){
            for (int i= 0; i < o.length; i++){
                System.out.println(o[i]);
            }
            
            
        }
        System.out.println("-----\n");
        return "home";
    }
}
