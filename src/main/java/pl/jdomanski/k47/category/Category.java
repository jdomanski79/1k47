package pl.jdomanski.k47.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="categories")
public class Category {
   
   // == fields ==
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;
    
    @NotBlank
    private String name;
    
    @NotNull
    private CategoryType type;
    
    // == public methods ==

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
        this.name = name.toLowerCase();
    }
    
    public CategoryType getType(){
        return this.type;
    }
    
    public void setType(CategoryType type){
        this.type = type;
    }
    
    public String toString(){
        return "Category: id: " + this.id + ", name: " + this.name + ", type: " + this.type.getSingularDescription();
    }
}
