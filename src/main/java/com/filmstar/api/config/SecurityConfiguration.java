package com.filmstar.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.filmstar.api.config.jwt.JwtAuthenticationEntryPoint;
import com.filmstar.api.config.jwt.JwtAuthenticationFilter;
import com.filmstar.api.entities.Role;
import com.filmstar.api.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
    UserService userService;
   
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        .and()
        .authorizeHttpRequests(request -> request
            .antMatchers("/api/v1/auth/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/favorites/**").hasAuthority(Role.ROLE_USER.toString()) 
            .antMatchers(HttpMethod.POST, "/api/v1/favorites/**").hasAuthority(Role.ROLE_USER.toString())
            .antMatchers(HttpMethod.DELETE, "/api/v1/favorites/**").hasAuthority(Role.ROLE_USER.toString())
            .antMatchers(HttpMethod.GET, "/api/v1/movies/**").hasAuthority(Role.ROLE_USER.toString()) 
            .antMatchers(HttpMethod.POST, "/api/v1/movies/{id}/rate").hasAuthority(Role.ROLE_USER.toString()) 
            .antMatchers(HttpMethod.POST, "/api/v1/movies").hasAuthority(Role.ROLE_ADMIN.toString()) 
            .antMatchers(HttpMethod.PUT, "/api/v1/movies/**").hasAuthority(Role.ROLE_ADMIN.toString())  
            .antMatchers(HttpMethod.DELETE, "/api/v1/movies/**").hasAuthority(Role.ROLE_ADMIN.toString()) 
            .anyRequest().authenticated())
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpFirewall looseHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true); // Permitir la doble barra
        return firewall;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}