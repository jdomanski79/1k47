package pl.jdomanski.k47.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @UniqueElements
    private String username;
    
    private String firstName;
    
    @Email
    private String email;
    
    private String password;
    
    @NotNull
    private boolean enabled;
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getFirstname(){
        return this.firstName;
    }
    
    public void setFirstname(String firstname){
        this.firstName = firstname;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public boolean isEnabled(){
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    
}
