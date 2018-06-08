package pl.jdomanski.k47.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
    public Category findByName(String name);
    public List<Category> findByType(CategoryType type);
        
}
