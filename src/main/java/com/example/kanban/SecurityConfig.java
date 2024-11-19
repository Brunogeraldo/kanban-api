package com.example.kanban;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/tasks/**").authenticated() // Protege os endpoints da API
                        .anyRequest().permitAll() // Permite acesso a outros endpoints
                )
                .httpBasic() // Habilita autenticação básica
                .and()
                .csrf().disable(); // Desabilita CSRF para simplificar a configuração (cuidado em produção)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Cria um usuário em memória com nome de usuário "user" e senha "password"
        manager.createUser (User.withUsername("user")
                .password(passwordEncoder().encode("password")) // A senha é codificada
                .roles("USER") // Atribui a role "USER"
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usado para codificar senhas
    }
}