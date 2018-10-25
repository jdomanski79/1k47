package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.jdomanski.k47.category.CategoryType;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    
    public List<Transaction> findAllByOrderByDateDesc();
    
    @Query("select t.category.id, t.category.name, sum(t.amount) from Transaction t " + 
        "where t.category.type = ?1 and Month(t.date) = ?2 and year(t.date) = ?3 " +
        "Group by t.category order by sum(t.amount) desc")
    public List<Object[]> findByTypeAndMonthAndYearSumAmount(CategoryType type, int month, int year);
    
    public Transaction findById(int id);
    
@Query("select t from Transaction t " +
"where (?1 is null or t.category.id = ?1) " + 
"and (?2 is null or month(t.date) = ?2)" + 
"and (?3 is null or year(t.date) = ?3)" + 
"order by t.date desc, t.id desc")
    public List<Transaction> findByParamsOrderByDateDesc(Integer categoryId, Integer month, Integer year);
}
