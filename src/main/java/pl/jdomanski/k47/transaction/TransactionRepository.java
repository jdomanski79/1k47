package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.jdomanski.k47.category.CategoryType;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    
    public List<Transaction> findAllByOrderByDateDesc();
    
    @Query("select t.category.id, t.category.name, sum(t.amount) from Transaction t " + 
        "where t.category.type = ?1 and Month(t.date) = Month(curdate()) and year(t.date) = year(curdate()) " +
        "Group by t.category order by sum(t.amount)")
    public List<Object[]> findByTypeAndSumAmountInCurrentMonth(CategoryType type);
    
    public Transaction findById(int id);
    
@Query("select t from Transaction t " +
"where (?1 is null or t.category.id = ?1) ")
//"and ("
//    and (@param2 is null or col2 = @param2)
    public List<Transaction> findByParams(Integer categoryId);
}
