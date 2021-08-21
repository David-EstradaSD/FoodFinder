package com.skilldistillery.foodfinder.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
 //       .antMatchers(HttpMethod.DELETE, "/api/address/donor/{addressId}").hasRole("admin") // Can access this routing when admin is in their role
        .antMatchers(HttpMethod.GET, "/api/service-locations").permitAll() // This ALLOWS the specified path to be permitted to access without authentication (aka to the general public)
        .antMatchers(HttpMethod.GET, "/api/services").permitAll() // This ALLOWS the specified path to be permitted to access without authentication (aka to the general public)
        .antMatchers(HttpMethod.POST, "/api/register").permitAll() // This ALLOWS the specified path to be permitted to access without authentication (aka to the general public)
        .antMatchers(HttpMethod.GET, "/api/ratings/{locationName}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/service-locations").hasRole("donor")
        .antMatchers(HttpMethod.POST, "/api/services").hasRole("admin")
        .antMatchers(HttpMethod.GET, "/api/donors/{category}").permitAll()
        .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
        .anyRequest().permitAll()               // All other requests are allowed without authorization.
        .and()
        .httpBasic();                           // Use HTTP Basic Authentication as opposed to OAuth, Digest, etc

        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT username, password, enabled FROM User WHERE username=?";
        String authQuery = "SELECT username, role FROM User WHERE username=?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
    }
}
