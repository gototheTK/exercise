package org.port.folio.post.controller;

import org.port.folio.post.model.User;
import org.port.folio.post.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "/auth/loginForm";
    }

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "/auth/joinForm";
    }

    @Secured("ROLE_USER")
    @GetMapping("/auth/updateForm")
    public String updateForm(){
        return "/auth/updateForm";
    }

    

}
