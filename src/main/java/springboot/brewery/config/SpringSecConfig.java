/*
 * package springboot.brewery.config;
 * 
 * import java.util.Arrays;
 * 
 * import org.springframework.boot.web.servlet.FilterRegistrationBean; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityCustomizer; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.util.matcher.AntPathRequestMatcher; import
 * org.springframework.web.cors.CorsConfiguration; import
 * org.springframework.web.cors.CorsConfigurationSource; import
 * org.springframework.web.cors.UrlBasedCorsConfigurationSource; import
 * org.springframework.web.filter.CorsFilter;
 * 
 * 
 *//**
	 * Created by jt, Spring Framework Guru.
	 *//*
		 * @Configuration
		 * 
		 * @EnableWebSecurity public class SpringSecConfig {
		 * 
		 * private static final String[] AUTH_WHITELIST = { // -- Swagger UI v2
		 * "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
		 * "/configuration/ui", "/configuration/security", "/swagger-ui.html",
		 * "/webjars/**", // -- Swagger UI v3 (OpenAPI) "/v3/api-docs/**",
		 * "/swagger-ui/**", // -- Authorization server "/oauth2/token/revokeById/**",
		 * "/tokens/**", // other public endpoints "/h2-console/**"
		 * 
		 * // other public endpoints of your API may be appended to this array };
		 * 
		 * 
		 * @Bean public WebSecurityCustomizer webSecurityCustomizer() { return (web) ->
		 * web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
		 * }
		 * 
		 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
		 * Exception { http.csrf().disable(). authorizeHttpRequests()
		 * .requestMatchers(AUTH_WHITELIST) .permitAll() .anyRequest().authenticated()
		 * .and().oauth2ResourceServer().jwt();
		 * 
		 * // Enable and configure CORS
		 * http.cors().configurationSource(corsConfigurationSource());
		 * 
		 * // Make communication STATELESS
		 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
		 * STATELESS);
		 * 
		 * http.headers(headers -> headers.frameOptions().disable());
		 * 
		 * return http.build(); }
		 * 
		 * 
		 * @Bean public WebMvcConfigurer corsConfigurer() { return new
		 * WebMvcConfigurer() {
		 * 
		 * @Override public void addCorsMappings(CorsRegistry registry) {
		 * registry.addMapping("/oauth2/token").allowedOrigins("http://localhost:8080");
		 * } }; }
		 * 
		 * 
		 * @Bean CorsConfigurationSource corsConfigurationSource() { // Very permissive
		 * CORS config... final var configuration = new CorsConfiguration();
		 * configuration.setAllowedOrigins(Arrays.asList("*"));
		 * configuration.setAllowedMethods(Arrays.asList("*"));
		 * configuration.setAllowedHeaders(Arrays.asList("*"));
		 * configuration.setExposedHeaders(Arrays.asList("*"));
		 * 
		 * // Limited to API routes (neither actuator nor Swagger-UI) final var source =
		 * new UrlBasedCorsConfigurationSource();
		 * source.registerCorsConfiguration("/**", configuration);
		 * 
		 * return source; }
		 * 
		 * 
		 * @Bean public FilterRegistrationBean corsFilter() {
		 * UrlBasedCorsConfigurationSource source = new
		 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
		 * CorsConfiguration(); config.setAllowCredentials(true);
		 * config.addAllowedOrigin("http://locahost"); config.addAllowedHeader("*");
		 * config.addAllowedMethod("*"); source.registerCorsConfiguration("/**",
		 * config); FilterRegistrationBean bean = new FilterRegistrationBean(new
		 * CorsFilter(source)); bean.setOrder(0); return bean; }
		 * 
		 * 
		 * 
		 * }
		 */