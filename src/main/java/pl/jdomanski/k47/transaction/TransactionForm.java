package pl.jdomanski.k47.transaction;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


public class TransactionForm {

	    @NotNull
	    @DateTimeFormat(pattern="yyyy-mm-dd")
	    @PastOrPresent
	    private LocalDate date;

	    private String description;
	    
	    @NotNull
	    @NumberFormat
	    private Long amount;
	    
	    @NotNull
	    private String category;


		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}


		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}
		
		public void setDate(LocalDate date) {
			this.date = date;
		}

		public void setAmount(Long amount) {
			this.amount = amount;
		}

		public LocalDate getDate() {
			return date;
		}

		public Long getAmount() {
			return amount;
		}
}
