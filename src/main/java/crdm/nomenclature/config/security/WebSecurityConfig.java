package crdm.nomenclature.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import crdm.nomenclature.filter.CharacterSetFilter;


@Configuration
@ComponentScan(basePackages = { "crdm.nomenclature.security" })
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterBefore(new CharacterSetFilter(), BasicAuthenticationFilter.class);
        http
        .authorizeRequests()
        	.antMatchers("/contract/**", "/provider/**", "/section/**").access("hasAnyAuthority('WRITE_PRIVILEGE')")
        	.antMatchers("/request/**").access("hasAnyAuthority('APPROVE_PRIVILEGE')")
        	.antMatchers("/purchase/**").access("hasAnyAuthority('PURCHASE_REQUEST_PRIVILEGE')")
        	.antMatchers("/settings/**").access("hasAnyAuthority('SETTINGS_PRIVILEGE')")
        	.anyRequest()
        	.authenticated()
        	.and()
        .formLogin()
        	.loginPage("/login")
        	.permitAll()
        	.and()
        .logout()
        	.permitAll()
        	.and()
        .csrf().disable();

	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/resources/**");
    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	}
	
	
}
