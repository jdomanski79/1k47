package pl.jdomanski.k47.transaction;



import static java.time.LocalDate.now;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import pl.jdomanski.k47.category.Category;

@Data
@Entity
@Table(name="TRANSACTIONS")
public class Transaction {
    
    // == fields ==
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int id;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    private LocalDate created = now();

    private String description;
    
    private Long amount;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
    // == constructors == 
    public Transaction (LocalDate date, String description, Long amount){
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    // == public methods ==
    public String getShortDate(){
        String[] months = {"sty", "lut", "mar", "kwi", "maj", "cze", "lip", "sie", "wrz", "paź", "lis", "gru"};
        return this.date.getDayOfMonth() + " " + months[this.date.getMonthValue() - 1];
    }

// getters and setters


//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public LocalDate getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDate created) {
//        this.created = created;
//    }
//
//    public int getId() {
//        return id;
//
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    
//    public Long getAmount(){
//        return amount;
//    }
//    
//    public void setAmount(Long amount){
//        this.amount = amount;
//    }
//    
//    public void setAmount(float amount){
//        this.amount = (long) (amount*100);
//    }
//    
//    public Category getCategory(){
//        return category;
//    }
//    
//    public void setCategory(Category category){
//        this.category = category;
//    }
//    

//    
//	@Override
//	public String toString() {
//		return "Transaction [id=" + id + ", date=" + date + ", created=" + created + ", description=" + description
//				+ ", amount=" + amount + ", category=" + category + "]";
//	}
//


}
