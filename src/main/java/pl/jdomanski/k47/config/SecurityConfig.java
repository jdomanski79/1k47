//package pl.jdomanski.k47.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//// @EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    
//    @Autowired
//    DataSource dataSource;
//    
//    @Override
//    public void configure(HttpSecurity http) throws Exception{
//        http
//            .authorizeRequests()
//                .antMatchers("/static").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//            .and()
//            .rememberMe()
//            .and()
//            .csrf().disable();
//    }
//    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        
//        UserBuilder user = User.withDefaultPasswordEncoder();
//        
//        auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, enabled from users where username=?")
//            .inMemoryAuthentication()
//                .withUser("jarek").password("12345").roles("USER");
//    }
//    
//}
