package pl.jdomanski.k47.category;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
	
	@ModelAttribute("categoryTypes")
	public List<CategoryType> getTypes(){
		return Arrays.asList(CategoryType.values());
	}
	@ModelAttribute("title")
	public String title(){
		return "Wprowadź kategorię";
	}
	
	
	@GetMapping("/category")
	public String categoryGet(Category category) {
		return "category.form";
	}
	
	@PostMapping("/category")
	public String categoryPost(Category category, 
			BindingResult bindingresult,
			Model model) {
		if (bindingresult.hasErrors()) {
			return "category.form";
		}
		
		return "home";
	}
}
