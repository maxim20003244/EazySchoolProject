package com.eazybyte.springschoolproject.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig  {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers(PathRequest.toH2Console()).and().
                authorizeHttpRequests()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/displayMessages").hasRole("ADMIN")
                .requestMatchers("/display").hasRole("ADMIN")
                .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                .requestMatchers("/employee").permitAll()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/holiday/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .and().formLogin().loginPage("/login")
                       .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()

                .and().httpBasic();
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }

@Bean
        public InMemoryUserDetailsManager userDetailsService(){
            UserDetails admin = User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("12345")
                    .roles("USER")
                    .build();

            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("54321")
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user,admin);

        }

}
