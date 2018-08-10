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
import lombok.ToString;

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
    @ToString.Exclude
    private Category category;
    
    // == constructors == 
//    public Transaction (LocalDate date, Long amount, String description){
//        this.date = date;
//        this.description = description;
//        this.amount = amount;
//    }

    // == public methods ==
    public String getShortDate(){
        String[] months = {"sty", "lut", "mar", "kwi", "maj", "cze", "lip", "sie", "wrz", "paź", "lis", "gru"};
        return this.date.getDayOfMonth() + " " + months[this.date.getMonthValue() - 1];
    }
}
