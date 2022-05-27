package com.bestwisher.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bestwisher.config.security.jwt.AuthEntryPointJWT;
import com.bestwisher.config.security.jwt.AuthTokenFilter;
import com.bestwisher.repository.AppUserRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = AppUserRepository.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private AuthEntryPointJWT unauthorizedHandler;

	@Autowired
	private Environment env;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
	
		// We don't need CSRF for this example
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/api/user/auth/**", "/api/image/**", "/ws").permitAll()
		// all other requests need to be authenticated
		.anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to store user's state.
		exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsFilter corsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	//config.setAllowCredentials(true);
	config.addAllowedOrigin("*");
	config.addAllowedHeader("*");
	config.addAllowedMethod("OPTIONS");
	config.addAllowedMethod("GET");
	config.addAllowedMethod("POST");
	config.addAllowedMethod("PUT");
	config.addAllowedMethod("DELETE");
	source.registerCorsConfiguration("/**", config);
	return new CorsFilter(source);
	}
	

}
