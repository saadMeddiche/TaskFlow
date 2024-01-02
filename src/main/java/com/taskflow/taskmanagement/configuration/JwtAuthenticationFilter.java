package com.taskflow.taskmanagement.configuration;

import com.taskflow.taskmanagement.services.JwtService;
import com.taskflow.taskmanagement.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private UserService userService;

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // Predicate to check if authorization should be skipped
    Predicate<String> shouldSkipAuthorization = (authHeader) -> authHeader == null || !authHeader.startsWith("Bearer ");

    // Predicate to check if authentication is not required
    Predicate<String> isAuthenticationNotRequired = userEmail -> userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null;

    // BiPredicate to check if the token is not valid
    BiPredicate<String, UserDetails> tokenIsNotValid = (jwt, userDetails) ->
            !jwtService.isTokenValid(jwt, userDetails);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        // If authorization should be skipped, continue with the filter chain
        if (shouldSkipAuthorization.test(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT token from the Authorization header
        String jwt = extractJwtToken(authHeader);

        // Extract user email from the JWT token
        String userEmail = jwtService.extractUserName(jwt);

        // If authentication is not required, continue with the filter chain
        if (isAuthenticationNotRequired.test(userEmail)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Load user details using the user email
        UserDetails userDetails = loadUserDetails(userEmail);

        // If the token is not valid, continue with the filter chain
        if (tokenIsNotValid.test(jwt, userDetails)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Set the authentication context
        setAuthenticationContext(request, userDetails);

        // Continue with the filter chain
        filterChain.doFilter(request, response);

    }

    // Extracts the JWT token from the Authorization header
    private String extractJwtToken(String authHeader) {
        return authHeader.substring(7);
    }

    // Extracts the user email from the JWT token
    private String extractUserEmail(String jwt) {
        return jwtService.extractUserName(jwt);
    }

    // Loads user details using the user email
    private UserDetails loadUserDetails(String userEmail) {
        return userService.userDetailsService().loadUserByUsername(userEmail);
    }

    // Sets the authentication context using the provided request and user details
    private void setAuthenticationContext(HttpServletRequest request, UserDetails userDetails) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}
