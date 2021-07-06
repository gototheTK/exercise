package org.port.folio.post.controller.api;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.port.folio.post.model.User;
import org.port.folio.post.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserApiController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticator;

    @PostMapping(path = "/auth/join", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void userJoin(User user, HttpServletResponse response) throws IOException{

        
        
        userService.회원가입(user);

        response.sendRedirect("/");

    }


    @PostMapping("/auth/update")
    public void userUpdate(User user, HttpServletResponse response) throws IOException{

        userService.회원정보수정(user);

        Authentication authentication = authenticator.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.sendRedirect("/auth/updateForm");

    }



}
