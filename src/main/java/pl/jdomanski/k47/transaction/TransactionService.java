package pl.jdomanski.k47.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import lombok.extern.slf4j.Slf4j;

@Service
@SessionScope
@Slf4j
public class TransactionService {
    
	// == fields ==
    private final TransactionRepository transactionRepository;
    
    // == constructors ==
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("Transaction service for user {} constructed!", auth.getName());
	}
    
    // == public methods ==
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
