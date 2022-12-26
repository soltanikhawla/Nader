package com.Libra.khawla.ConfigSecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
	private final JwtRequestFilter jwtRequestFilter;

	private static String Login;
	private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
			new User(
					"khawla",
					"password",
					Collections.singleton(new SimpleGrantedAuthority("Role_Admin"))
			),
			new User(
					"user",
					"password",
					Collections.singleton(new SimpleGrantedAuthority("Role_User"))
			)

	);

	public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http

				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic();
		return http.build();
	}


	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(usersDetailsService());

		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	@Bean
	 private PasswordEncoder passwordEncoder (){
		return NoOpPasswordEncoder.getInstance();
	};

	@Bean
	public UserDetailsService usersDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String Login) throws UsernameNotFoundException {
				return APPLICATION_USERS
						.stream()
						.filter(u -> u.getUsername().equals(Login))
						.findFirst()
						.orElseThrow(()-> new UsernameNotFoundException("No user found with Login"));

			}
		};
	}
}