package pl.jdomanski.k47.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.jdomanski.k47.transaction.TransactionRepository;



@Service
public class CategoryService {
    
    final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public boolean save(Category category){
        
        if (categoryRepository.findByName(category.getName()) != null){
           logger.info("Found catgory with that name. Aborting save..");
           return false;
       }
        
        logger.info("Saving category: " + category.getName());
        categoryRepository.save(category);
        
        return true;
    }
     
    public Map< CategoryType, List<Category> > getCategoriesGroupedByType(){
        // get categories from database
        List<Category> incomes = categoryRepository.findByType(CategoryType.INCOME);
	    List<Category> outcomes = categoryRepository.findByType(CategoryType.OUTCOME);
	    
	    // and put them into map
	    Map<CategoryType, List <Category> > result = new HashMap<>();
	    
	    result.put(CategoryType.INCOME, incomes);
	    result.put(CategoryType.OUTCOME, outcomes);
	    
	    return result;
    }
    
    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    
    public Category findById(int id){
        return categoryRepository.findById(id);
    }
    
    public void update(Category category){
        logger.info("Updating category: " + category );
        categoryRepository.save(category);
    }

    public Model findAndSumByIncome(Model model, int month, int year){
        
        
        List<Object[]> categories;
        long sum;
        int balance = 0;
        
        for (CategoryType type : CategoryType.values()){
            
            categories = transactionRepository.findByTypeAndMonthAndYearSumAmount(type, month, year);
            sum = 0;
            for(Object[] c: categories){
                sum += (long) c[2];
            }
            
            if (type == CategoryType.INCOME) {
            	balance += sum;
            } else {
            	balance -= sum;
            }
            
            
            model.addAttribute(type.name(), categories);
            model.addAttribute(type.name()+ "Sum", sum);
        }
        
        model.addAttribute("balance", balance);
        
        
        return model;
    }
}
