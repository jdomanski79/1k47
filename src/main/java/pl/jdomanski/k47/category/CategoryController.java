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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	    
	    return "category/category.list";
	}
	
// mappings method
	@GetMapping
	public String categoryGet(Category category) {
		return "category/category.form";
	}
	
	@PostMapping
	public String categoryPost(@Valid Category category, 
			BindingResult bindingresult,
			RedirectAttributes redirectAttributes) {
		if (bindingresult.hasErrors()) {
			return "category/category.form";
		}
		
		if (!categoryService.save(category)){
		  bindingresult.rejectValue("name", "name exists", "Problem z zapisem. Taka kategoria już istnieje?");
		  return "category/category.form";
		}
		
		redirectAttributes.addFlashAttribute("message", "Wprowadzono nową kategorię!");
		return "redirect:/category/list";
	}
}
