package com.taskflow.taskmanagement.configuration;

import com.taskflow.taskmanagement.permissions.TagPermissions;
import com.taskflow.taskmanagement.permissions.TaskPermissions;
import com.taskflow.taskmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(

                        request -> request
                        .requestMatchers("/api/v1/authentication/**").permitAll()
                        .requestMatchers("/api/v1/resource/home").permitAll()
                        .requestMatchers("/api/v1/resource/account").authenticated()
                        .requestMatchers("/api/v1/resource/admin").hasAuthority("*")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/tags").hasAnyAuthority(TagPermissions.ADD_TAG.name() , "*")
                        .requestMatchers(HttpMethod.DELETE ,"/api/v1/tags/**").hasAnyAuthority(TagPermissions.DELETE_TAG.name() , "*")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/tasks").hasAnyAuthority(TaskPermissions.CREATE_TASK.name() , "*")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/tasks/assignTask").hasAnyAuthority(TaskPermissions.ASSIGN_TASK.name() , "*")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/tasks/assignAdditionalTask").hasAnyAuthority(TaskPermissions.ASSIGN_ADDITIONAL_TASK.name() , "*")
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(configurer -> configurer
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService.userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("[ \"You do not have permission to access this resource.\" ]");
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("[ \"You must be authenticated to access this resource.\" ]");
        };
    }

}
