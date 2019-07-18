package otus.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfiguration{
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/").authenticated()
                .pathMatchers("/index.css").authenticated()
                .pathMatchers("/user.js").authenticated()
                .pathMatchers("/genre.js").authenticated()
                .pathMatchers("/author.js").authenticated()
                .pathMatchers("/api/users").authenticated()
                .pathMatchers("/api/genres").authenticated()
                .pathMatchers("/flux/authors").authenticated()
                .and().formLogin()
                .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(admin);
    }
}
