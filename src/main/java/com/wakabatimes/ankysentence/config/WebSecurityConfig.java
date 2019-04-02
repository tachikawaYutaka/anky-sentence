package com.wakabatimes.ankysentence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;
import static com.wakabatimes.ankysentence.config.SecurityConstants.LOGIN_URL;
import static com.wakabatimes.ankysentence.config.SecurityConstants.SIGNUP_URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().authorizeRequests()
                .antMatchers(API_URL + "/user/csrf-token",
                        API_URL + "/user/remind",
                        API_URL + "/user/password-reset",
                        API_URL + "/users/activate/**",
                        API_URL + SIGNUP_URL,
                        API_URL + LOGIN_URL,
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and().csrf().disable()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;
    }


    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
}

