package pl.jdomanski.k47.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
                .antMatchers("/static").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            .and()
            .rememberMe()
            .and()
            .csrf().disable();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.authenticationProvider(authenticationProvider()); 
//        auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, enabled from users where username=?")
//            // i don't uses roles so next query gets static "ROLE_USER"
//            .authoritiesByUsernameQuery("select username, 'ROLE_USER' from users where username=?")
//            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    	authProvider.setUserDetailsService(userDetailsService);
    	return authProvider;
    }
    
    
}