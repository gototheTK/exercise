package org.port.folio.post.handler;

import javax.servlet.http.HttpServletRequest;

import org.port.folio.post.config.SecurityExpress;
import org.port.folio.post.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestController
public class SecurityDefaultExpression {

    SecurityExpress express;

    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentExceoption(Exception e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ModelAttribute
    public void securityDefaultExpression(HttpServletRequest request, Model model){

        express = new SecurityExpress(SecurityContextHolder.getContext());

        model.addAttribute("isLoggedIn", express.isAuthenticated() && !express.isAnonymous());
        model.addAttribute("username", express.getUsername());
        model.addAttribute("principal", express.getPrincipal());
        model.addAttribute("isAdmin", express.isAdmin());
    }
    
}
