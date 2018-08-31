package pl.jdomanski.k47;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jdomanski.k47.category.CategoryService;
import pl.jdomanski.k47.user.User;

@Controller
@RequestMapping("/")
public class HomeController {
    
	// == fields ==
	private final CategoryService categoryService;
    
    // == constructors ==
    @Autowired
    public HomeController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    // == handler methods ==
	@GetMapping
    public String home(Model model, Authentication auth){
        
		
		
        model = categoryService.findAndSumByIncomeInCurrentMonth(model);
        model.addAttribute("currentMonth", LocalDate.now().getMonthValue());
        model.addAttribute("currentYear", LocalDate.now().getYear());
        
        User user = (User) auth.getPrincipal();
        model.addAttribute("userFirstName", user.getFirstName());
        
        return "home";
    }
}
