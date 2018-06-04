package pl.jdomanski.k47.transaction;



import javax.persistence.*;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@Entity
@Table(name="TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="created")
    private LocalDate created = now();

    @Column(name="description")
    private String description;

    @Column(name="value")
    private long value;


// getters and setters


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
