// package com.easysoftware.sgi_api.config;

// import java.util.Arrays;
// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// @Configuration
// public class WebConfig {

//     @Bean
//     public CorsFilter corsFilter() {
//         CorsConfiguration config = new CorsConfiguration();
//         config.setAllowedOrigins(List.of("http://localhost:4200"));
//         config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//         config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
//         config.setAllowCredentials(true);
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", config);
//         return new CorsFilter(source);
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())) 
//             // O comando acima diz ao Spring Security para usar a config de CORS que criamos
//             .csrf(csrf -> csrf.disable()) // Geralmente desativado para APIs REST (Stateless)
//             .authorizeHttpRequests(auth -> auth
//                 .anyRequest().authenticated()
//             );
//         return http.build();
//     }
// }
