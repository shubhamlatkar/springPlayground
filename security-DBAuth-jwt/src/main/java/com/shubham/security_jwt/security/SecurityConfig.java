package com.shubham.security_jwt.security;

import com.shubham.security_jwt.security.jwt.CORSFilter;
import com.shubham.security_jwt.security.config.PasswordConfig;
import com.shubham.security_jwt.security.jwt.JwtReqFilter;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtReqFilter jwtReqFilter;
    private final PasswordConfig passwordConfig;
    private final CORSFilter corsFilter;

    @Autowired
    public SecurityConfig(JwtReqFilter jwtReqFilter, PasswordConfig passwordConfig, CORSFilter corsFilter) {
        this.jwtReqFilter = jwtReqFilter;
        this.passwordConfig = passwordConfig;
        this.corsFilter = corsFilter;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(passwordConfig);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .anyRequest()
                .authenticated()
                .antMatchers("/test/**").hasRole("USER")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return provider;
    }

}
