package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.jdomanski.k47.category.CategoryType;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    
    public List<Transaction> findAllByOrderByDateDesc();
    
    @Query("select t.category.name, sum(t.amount) from Transaction t " + 
        "where t.category.type = ?1 and Month(t.date) = Month(curdate()) and year(t.date) = year(curdate()) " +
        "Group by t.category order by sum(t.amount)")
    public List<Object[]> findByTypeAndSumAmountInCurrentMonth(CategoryType type);
    
    public Transaction findById(int id);
}
