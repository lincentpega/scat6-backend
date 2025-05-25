package com.lincentpega.scat6backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String requestId = generateRequestId();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String remoteAddr = getClientIpAddress(request);
        
        // Log request start
        log.info("Request [{}] started: {} {} from {}", requestId, method, uri, remoteAddr);
        
        try {
            filterChain.doFilter(request, response);
        } finally {
            // Log authentication details after processing
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            logAuthenticationDetails(requestId, auth, response.getStatus());
        }
    }

    private void logAuthenticationDetails(String requestId, Authentication auth, int responseStatus) {
        if (auth == null || !auth.isAuthenticated()) {
            log.info("Request [{}] completed with status {} - No authentication", requestId, responseStatus);
            return;
        }

        String principal = auth.getName();
        String authorities = auth.getAuthorities().toString();
        
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String subject = jwt.getSubject();
            String issuer = jwt.getIssuer() != null ? jwt.getIssuer().toString() : "unknown";
            
            // Extract additional claims safely
            String email = getClaimAsString(jwt, "email");
            String preferredUsername = getClaimAsString(jwt, "preferred_username");
            String sessionState = getClaimAsString(jwt, "session_state");
            
            log.info("Request [{}] completed with status {} - JWT Auth: subject={}, principal={}, " +
                    "email={}, username={}, authorities={}, issuer={}, sessionState={}", 
                    requestId, responseStatus, subject, principal, email, preferredUsername, 
                    authorities, issuer, sessionState);
        } else {
            log.info("Request [{}] completed with status {} - Auth: principal={}, authorities={}, type={}", 
                    requestId, responseStatus, principal, authorities, auth.getClass().getSimpleName());
        }
    }

    private String getClaimAsString(Jwt jwt, String claimName) {
        return Optional.ofNullable(jwt.getClaim(claimName))
                .map(Object::toString)
                .orElse("N/A");
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }

    private String generateRequestId() {
        return String.valueOf(System.currentTimeMillis() % 100000);
    }
} 