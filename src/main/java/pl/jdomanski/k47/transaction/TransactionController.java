package pl.jdomanski.k47.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.time.LocalDate.now;

@Controller
public class TransactionController {

    @Autowired
    TransactionRepository repository;

    @RequestMapping("/")
    public Transaction homeController(){
        Transaction transaction = new Transaction();

        transaction.setDescription("Dane");
        transaction.setDate(now());
        repository.save(transaction);

        return transaction;

    }
}
