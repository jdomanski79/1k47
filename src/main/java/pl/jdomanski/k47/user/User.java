package pl.jdomanski.k47.user;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int id;
    
    private String login;
    
    private String name;
    
    private String email;
    
    private String hash;
    
    
}
