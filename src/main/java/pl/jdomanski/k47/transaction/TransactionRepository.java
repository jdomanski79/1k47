package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    
    public List<Transaction> findAllByOrderByDateDesc();
}
