package pl.jdomanski.k47;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import pl.jdomanski.k47.category.CategoryService;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    
	// == fields ==
	private final CategoryService categoryService;
    
	
	@Value("${history.start}")
	private int firstYear;
	
    // == constructors ==
    @Autowired
    public HomeController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    // == handler methods ==
	@GetMapping
    public String home(Model model, @RequestParam(required=false) Integer month, 
    		@RequestParam(required=false) Integer year){
        
		
		if (month == null) {
			month = LocalDate.now().getMonthValue();
		}
		if (year == null) {
			year = LocalDate.now().getYear();
		}
        
		model = categoryService.findAndSumByIncome(model, month, year);
        
		model.addAttribute("years", getYears());
		model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedYear", year);
        
        return "home";
    }
	
	private ArrayList<Integer> getYears(){
		ArrayList<Integer> years = new ArrayList();
		Integer currentYear = LocalDate.now().getYear();
		int year = firstYear;
		
		while (year <= currentYear) {
			years.add(year);
			year++;
		}
		return years;
 	}
}
