package pl.jdomanski.k47;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.CategoryService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CategoryService categoryService;
    
    
    
    @GetMapping
    public String home(Model model){
        
        model = categoryService.findAndSumByIncomeInCurrentMonth(model);
            
        System.out.println(model);            

        return "home";
    }
}
