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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	

// mappings methods
	@GetMapping("/list")
	public String categoryList(Model model){
	    
	    model.addAttribute("categories", categoryService.getCategoriesGroupedByType());
	    
	    return "category/category.list";
	}
	

	@GetMapping
	public String categoryGet(Category category, Model model) {
		model.addAttribute("title", "Wprowadź nową kategorie");
		model.addAttribute("form_method", "post");
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
	
	@GetMapping("/{id}")
	public String categoryDetailsGet(@PathVariable int id, Model model){
	    Category category = categoryService.findById(id);
	    
	    if (category != null){
	       model.addAttribute("category", category);
        }

	    
	    return "category/category.details";
	}
	
	@GetMapping("/{id}/update")
	public String categoryUpdateGet(@PathVariable int id, Model model){
	    
	    Category category = categoryService.findById(id);
	    
	    
	    if (category != null){
	        model.addAttribute("category", category);
	        model.addAttribute("form_method", "put");
	        model.addAttribute("title", "Wprowadź zmiany w kategorii");
	    }
	    
	    return "category/category.form";
	}

    @PutMapping("/{id}/update")
    public String categoryUpdatePut(
        @Valid Category category, 
        RedirectAttributes redirectAttributes){
        
        categoryService.update(category);
        
        redirectAttributes.addFlashAttribute("message", "Zaktualizowano kategorie " + category.getName());
        
        return "redirect:/category/list";
    }
    
}
