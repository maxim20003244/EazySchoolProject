package com.eazybyte.springschoolproject.config;


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
        httpSecurity.csrf().ignoringRequestMatchers("/saveMsg").and().
                authorizeHttpRequests()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/holiday/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .and().formLogin().loginPage("/login")
                       .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
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
                    .roles("USER","ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user,admin);

        }

}
