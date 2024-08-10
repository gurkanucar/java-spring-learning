package org.gucardev.securityexample4.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gucardev.securityexample4.service.JwtDecoderService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtDecoderService jwtDecoderService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = header.substring(7);

        try {
            String username = jwtDecoderService.extractUsername(jwt);
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            if (!jwtDecoderService.isTokenValid(jwt, userDetails.getUser().getTokenSign())) {
                throw new JwtException("Invalid JWT");
            }

//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            CustomUsernamePasswordAuthenticationToken authToken =
                    new CustomUsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities(), jwt);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        } catch (Exception e) {
            sendError(response, e);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.println(mapper.writeValueAsString(Map.of("error", e.getMessage())));
        out.flush();
    }
}

