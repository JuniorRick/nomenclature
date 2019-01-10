package crdm.nomenclature.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@ComponentScan(basePackages = { "crdm.nomenclature.security" })
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

        http
        .authorizeRequests()
        	.antMatchers("/contract/**", "/provider/**").hasAnyRole("ADMIN", "JURIST", "CONTABIL")
        	.antMatchers("/request/**").hasAnyRole("ADMIN", "JURIST", "CONTABIL", "SEF")
        	.antMatchers("/section/**").hasAnyRole("ADMIN", "JURIST")
        	.antMatchers("/settings/**").hasRole("ADMIN")
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
	
	protected UserDetailsService userDetailService() {
		
		UserDetailsService userDetails =  (UserDetailsService)SecurityContextHolder.getContext().getAuthentication();
		
		return userDetails;
	}
	
}
