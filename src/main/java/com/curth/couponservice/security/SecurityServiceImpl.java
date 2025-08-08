package com.curth.couponservice.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    final SecurityContextRepository securityContextRepository;

    @Autowired
    public SecurityServiceImpl(final SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }


    @Override
    public boolean login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        log.debug("Authentication Token: {}", authenticationToken);
        System.out.println("Authentication Token: " + authenticationToken);

        authenticationManager.authenticate(authenticationToken);
        boolean authenticated = authenticationToken.isAuthenticated();
        if (authenticated) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationToken);
            securityContextRepository.saveContext(context, request, response);
        }
        return authenticated;
    }
}
