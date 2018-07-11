package pl.jdomanski.k47.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }
    
    public Iterable<Transaction> findAll(){
        return transactionRepository.findAllByOrderByDateDesc();
    }
    
    public Transaction findById(int id){
        return transactionRepository.findById(id);
    }
}
