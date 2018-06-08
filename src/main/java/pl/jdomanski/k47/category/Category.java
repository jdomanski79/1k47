package pl.jdomanski.k47.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="categories")
public class Category {
    
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private int id;
    
    @NotBlank
    private String name;
    
    private CategoryType type;
    
    

    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public CategoryType getType(){
        return this.type;
    }
    
    public void setType(CategoryType type){
        this.type = type;
    }
}
