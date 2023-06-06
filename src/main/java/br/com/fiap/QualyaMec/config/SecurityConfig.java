package br.com.fiap.QualyaMec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests().anyRequest().permitAll().
        and().
        csrf().disable().
        formLogin().disable().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and().
        build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString() + "@";
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
            
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}

// @Configuration
// public class SecurityConfig {
    
//     @Autowired
//     AuthorizationFilter authorizationFilter;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
//         return http
//                 .authorizeRequests()
//                 .requestMatchers(HttpMethod.POST, "/api/registrar").permitAll()
//                     .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
//                     .anyRequest().permitAll()
//                 .and()
//                 .csrf().disable()
//                 .formLogin().disable()
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .headers().frameOptions().sameOrigin()
//                 .and()
//                 .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                 .build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
//         return config.getAuthenticationManager();
//     }
    
//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
    
// }
