package com.creatingBean.CreatingBean.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.creatingBean.CreatingBean.constant.EmployeePersmission;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EmployeeSecurity extends WebSecurityConfigurerAdapter {

	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeSecurity(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http
			.authorizeRequests()
				.antMatchers("/", "/employee").permitAll()
				.anyRequest().authenticated().and()
			.formLogin()
				.loginPage("/hello")
				.permitAll()
				.and()
			.logout()
				.permitAll();
				*/
		
		http.csrf().disable().authorizeRequests()
		//.antMatchers("/api/**").hasRole(EmployeePersmission.USER.name())
		.antMatchers(HttpMethod.POST, "/api/**").hasAuthority(EmployeePersmission.ADMIN.name())
		.antMatchers(HttpMethod.GET,"api/v1/**").hasAnyRole(EmployeePersmission.ADMIN.name(), EmployeePersmission.USER.name())
		//.antMatchers(HttpMethod.GET,"api/v1/employee/**").hasAuthority(EmployeePersmission.ADMIN.name())
		.anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.builder()
				.username("user")
				.password(passwordEncoder.encode("password"))
				//.roles(EmployeePersmission.USER.name())
				.authorities(EmployeePersmission.USER.name())
				.build();
		
		
		UserDetails admin =
				 User.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					//.roles(EmployeePersmission.ADMIN.name())
					.authorities(EmployeePersmission.ADMIN.name())
					.build();

  		return new InMemoryUserDetailsManager(user, admin);
	}
	
}
