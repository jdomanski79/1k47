package pl.jdomanski.k47.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



class MyUserDetails implements UserDetails{
    private User user;
    
    public MyUserDetails(User user){
        this.user = user;
    }
    
    public String getUsername(){
        return this.user.getUsername();
    }
    
    public String getPassword(){
        return this.user.getPassword();
    }
    
    public boolean isEnabled(){
        return true;
    }
    
    public boolean isAccountNonLocked(){
        return true;
    }
    
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    public boolean isAccountNonExpired(){
        return true;
    }
    
    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }
}
