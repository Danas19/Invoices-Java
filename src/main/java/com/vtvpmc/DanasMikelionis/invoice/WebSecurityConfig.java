package com.vtvpmc.DanasMikelionis.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	List<UserDetails> people = new ArrayList<>();
    	
    	UserDetails admin1 =
    		User.withDefaultPasswordEncoder()
    			.username("admin1")
    			.password("pass1")
    			.roles("ADMIN")
    			.build();
    	
        UserDetails user1 =
             User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .roles("USER")
                .build();
        
        UserDetails user2 =
        	User.withDefaultPasswordEncoder()
        		.username("petras")
        		.password("Use2!")
        		.roles("USER")
        		.build();
        
        people.add(admin1);
        people.add(user1);
        people.add(user2);
        
        return new InMemoryUserDetailsManager(people);
    }
}