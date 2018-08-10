package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

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
    
    public void deleteTransaction(int id) {
    	transactionRepository.deleteById(id);
    }
    
    public List<Transaction> findByParams(Integer categoryId, Integer month, Integer year){
    	return transactionRepository.findByParamsOrderByDateDesc(categoryId, month, year);
    }
    
}
