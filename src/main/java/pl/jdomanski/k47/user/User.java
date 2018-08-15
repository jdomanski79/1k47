package pl.jdomanski.k47.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	//== fields ==
	@Id
	private String username;
	
	private String password;
	
	@NotNull
	private boolean enabled;
	
	private String firstName;
}

// TODO change transaction list view

