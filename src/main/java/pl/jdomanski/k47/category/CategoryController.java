package pl.jdomanski.k47.category;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
    @Autowired
    CategoryService categoryService;


// model attributes	
	@ModelAttribute("categoryTypes")
	public List<CategoryType> getTypes(){
		return Arrays.asList(CategoryType.values());
	}
	
	@ModelAttribute("title")
	public String title(){
		return "Wprowadź kategorię";
	}
	
	
	@GetMapping("/list")
	public String categoryList(Model model){
	    
	    model.addAttribute("categories", categoryService.getCategoriesGroupedByType());
	    
	    return "category.list";
	}
	
// mappings method
	@GetMapping
	public String categoryGet(Category category) {
		return "category.form";
	}
	
	@PostMapping
	public String categoryPost(@Valid Category category, 
			BindingResult bindingresult,
			Model model) {
		if (bindingresult.hasErrors()) {
			return "category.form";
		}
		
		if (!categoryService.save(category)){
		  bindingresult.rejectValue("name", "name exists", "Problem z zapisem. Taka kategoria już istnieje?");
		  return "category.form";
		}
		
		return "redirect:/category/list";
	}
}
