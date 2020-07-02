package com.bohniman.vmsmaintenance.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfiguration
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    AuthenticationProvider authenticationProvider;

    private static Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    // ========================================================================
    // # Custom Authentication
    // ========================================================================
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] unloggedUrls = { "/", "/login", "/register", "/forgot-password", "/verifyOTP",
                "/changePassword", "/genCaptcha.png","/header-notifications", "/new" };

        http.authorizeRequests().antMatchers(unloggedUrls).permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable()
                .formLogin().loginPage("/")
                .successHandler(successHandler()).failureHandler(loginFailureHandler()).and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()
                .httpBasic().disable().headers()
                .frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/theme/**", "/custom/**", "/plugins/**", "/images/**");
    }

    @Bean
    public UrlAuthenticationSuccessHandler successHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, e) -> {
            response.sendRedirect("/?error=" + e.getMessage());
        };
    }
}