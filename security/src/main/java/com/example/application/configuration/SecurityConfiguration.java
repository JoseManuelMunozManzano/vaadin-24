package com.example.application.configuration;

import com.example.application.view.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// Este es el tercer paso, crear una clase de configuración pra habilitar Spring Security en nuestra app.
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // Esta es la view Login que hemos creado.
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}user").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
