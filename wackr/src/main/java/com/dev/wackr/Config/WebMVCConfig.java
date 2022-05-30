package com.dev.wackr.Config;

import javax.sql.DataSource;

import org.springframework.security.authentication.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebMVCConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

    @Override
protected void configure(final HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/*.css")
            .permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/**/wackr/api/**/new")
            .permitAll() 
        .and()
            .authorizeRequests()
            .antMatchers("/*orgorcust.html")
            .permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/*clientsignup.html")
            .permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/*orgsignup.html")
            .permitAll()
        .and()
            .authorizeRequests()
            .antMatchers("/error")
            .permitAll()
        .and()
            .antMatcher("/*")
            .authorizeRequests()
            // .anyRequest().authenticated()
        .and()
			.logout()
			.permitAll()
        .and()
            .httpBasic()
        .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .loginProcessingUrl("/login")
            .usernameParameter("username").passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true");
    }
}