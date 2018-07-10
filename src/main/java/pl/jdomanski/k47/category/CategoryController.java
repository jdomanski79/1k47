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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.jdomanski.k47.util.AttributeNames;
import pl.jdomanski.k47.util.Mappings;
import pl.jdomanski.k47.util.ViewNames;

@Controller
public class CategoryController {
	
	// == fields == 
    private final CategoryService categoryService;

    // == constructors ==
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    // == model attributes ==
	@ModelAttribute(AttributeNames.CATEGORY_TYPES)
	public List<CategoryType> getTypes(){
		return Arrays.asList(CategoryType.values());
	}
	
	// == handler methods ==
	@GetMapping(Mappings.CATEGORIES_LIST)
	public String categoryList(Model model){
	    
	    model.addAttribute(AttributeNames.CATEGORIES_BY_TYPE, categoryService.getCategoriesGroupedByType());
	    
	    return ViewNames.CATEGORIES_LIST;
	}
	

	@GetMapping(Mappings.CATEGORY_ADD)
	public String categoryGet(Category category, Model model) {
		model.addAttribute(AttributeNames.TITLE, "Wprowadź nową kategorie");
		return ViewNames.CATEGORIES_FORM;
	}
	
	@PostMapping(Mappings.CATEGORY_ADD)
	public String categoryPost(@Valid Category category, 
			BindingResult bindingresult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingresult.hasErrors()) {
			return ViewNames.CATEGORIES_FORM;
		}
		
		if (!categoryService.save(category)){
		  bindingresult.rejectValue("name", "name exists", "Problem z zapisem. Taka kategoria już istnieje?");
		  return ViewNames.CATEGORIES_FORM;
		}
		
		redirectAttributes.addFlashAttribute(AttributeNames.MESSAGE, "Wprowadzono nową kategorię!");
		
		return Mappings.CATEGORIES_LIST_REDIRECT;
	}
	
		
	@GetMapping(Mappings.CATEGORY_UPDATE)
	public String categoryUpdateGet(@PathVariable int id, Model model){
	    
	    Category category = categoryService.findById(id);
	    
	    
	    if (category != null){
	        model.addAttribute(AttributeNames.CATEGORY, category);
	        model.addAttribute(AttributeNames.TITLE, "Wprowadź zmiany w kategorii");
	    }
	    
	    return ViewNames.CATEGORIES_FORM;
	}

    @PutMapping(Mappings.CATEGORY_UPDATE)
    public String categoryUpdatePut( @Valid Category category, RedirectAttributes redirectAttributes){
        
        categoryService.update(category);
        
        redirectAttributes.addFlashAttribute(AttributeNames.MESSAGE, "Zaktualizowano kategorie " + category.getName());
        
        return Mappings.CATEGORIES_LIST_REDIRECT;
    }
    
}
