package com.olawhales.whales_ecommerce.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    @Autowired
    private JwtFilter jwtFilter ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter)throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/register/user/" ,"/api/register/login").permitAll()
                        .requestMatchers("/Product/create/").hasAuthority("SELLER")
                        .requestMatchers("/product/delete/").hasAuthority("SELLER")
                        .requestMatchers("/api/product/update/").hasAuthority("SELLER")
                        .requestMatchers("/api/product/findAll_product/").hasAuthority("SELLER")
                        .requestMatchers("/api/product/findSingle/").hasAuthority("SELLER")
                        .requestMatchers("/cart/addToCart/").permitAll()
                        .requestMatchers("/cart/removeFromCart/").permitAll()
                        .requestMatchers("/cart/deleteCart/").hasAuthority("BUYER")
                        .requestMatchers("/order/order/").hasAuthority("BUYER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(corsFilter() , SessionManagementFilter.class);
//                        .addFilterBefore(jwtFilter , SessionManagementFilter.class);
                        .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    private CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow cookies or credentials
        config.addAllowedOriginPattern("*"); // Allow all origins (replace * with specific domains in production)
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods
        source.registerCorsConfiguration("/**", config); // Apply this CORS configuration to all endpoints
        return new CorsFilter(source);
    }

        @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provide = new DaoAuthenticationProvider();
        provide.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provide.setUserDetailsService(userDetailsService);
        return provide;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
