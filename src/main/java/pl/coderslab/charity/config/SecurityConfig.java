package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/email", "/email/att", "/login", "/register/**", "/resources/**", "/login/**").permitAll()
                .antMatchers("/hello").permitAll()
                .antMatchers("/donation/**", "/user/setting/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .successHandler((request, response, authentication) -> {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    for (GrantedAuthority auth : authentication.getAuthorities()) {
                        if (auth.getAuthority().equals("ROLE_USER")) {
                            response.sendRedirect("/");
                        } else if (auth.getAuthority().equals("ROLE_ADMIN")) {
                            response.sendRedirect("/admin");
                        }
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?accessDenied=true") // Przekierowanie na stronę logowania w przypadku braku uprawnień
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        return http.build();
    }
}
