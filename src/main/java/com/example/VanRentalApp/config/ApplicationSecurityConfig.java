package com.example.VanRentalApp.config;

import com.example.VanRentalApp.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder
                                    ,ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                .formLogin()
//                    .loginPage("/login").permitAll()
//                    .defaultSuccessUrl("/van",true)
//                    .passwordParameter("password")
//                    .usernameParameter("username")
//                .and()
//                .rememberMe() // default 2 weeks
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                    .key("thisIsMyVerySecureKeyButNotAReallyGoneOne")
//                    .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID","remember-me")
//                    .logoutSuccessUrl("/login");


        ;
    }

    /*bellow code generates the users in a In memory database*/
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails alexUser = User.builder()
//                .username("Alex")
//                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name()) //ROLE_ADMIN
////                .authorities(ADMIN.getGrantedAuthorities()) //this method will get each permission's role, and concatenate the string "ROLE_" in front of it.
//                .build();
//
//        UserDetails andreeaUser = User.builder()
//                .username("Andreea")
//                .password(passwordEncoder.encode("password"))
//                .roles(OPERATOR.name()) //ROLE_OPERATOR
////                .authorities(OPERATOR.getGrantedAuthorities()) //this method will get each permission's role, and concatenate the string "ROLE_" in front of it.
//                .build();
//
//        UserDetails bogdanUser = User.builder()
//                .username("Bogdan")
//                .password(passwordEncoder.encode("password"))
//                .roles(USER.name()) //ROLE_USER
////                .authorities(USER.getGrantedAuthorities()) //this method will get each permission's role, and concatenate the string "ROLE_" in front of it.
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                alexUser, andreeaUser, bogdanUser
//        );
//
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
