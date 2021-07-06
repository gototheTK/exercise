package org.port.folio.post.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import org.port.folio.post.model.RoleType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;


public class SecurityExpress {

    private Authentication authentication;


    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    public SecurityExpress(SecurityContext context){
        authentication = context.getAuthentication();
        if(authentication != null){
            authorities = authentication.getAuthorities();
        }

    }

    public Object getPrincipal(){
        return authentication.getPrincipal();
    }

    public boolean isAuthenticated(){
        return authentication == null ? false : authentication.isAuthenticated();
    }

    public String getUsername() {
        return authentication == null ? "Anonymous" : authentication.getName();
    }

    public boolean isAnonymous() {
        return authentication == null ? true : authentication instanceof AnonymousAuthenticationToken;
    }

    public boolean isAdmin(){
        return hasAnyRole(Arrays.asList(RoleType.ADMIN));
    }

    public boolean hasAnyRole(List<RoleType> roles){
        return roles.stream().anyMatch(role -> hasRole(role));
    }

    public boolean hasRole(RoleType role){
        return authorities == null ? false : authorities.stream().anyMatch( authority -> authority.getAuthority().equals(role.name()));
    }



}
