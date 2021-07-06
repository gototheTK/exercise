package org.port.folio.post.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class FailureHandler implements AuthenticationFailureHandler{


    @Autowired
    BCryptPasswordEncoder encodePw;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub

        System.out.println("인코딩 비밀번호:"+ encodePw.encode(request.getParameter("1234")));
        System.out.println("인코딩 비밀번호:"+ encodePw.encode(request.getParameter("1234")));
        System.out.println("아이디: " + request.getParameter("username"));
        System.out.println("비밀번호: " + encodePw.encode(request.getParameter("password")));

        response.sendRedirect("/");
        
    }
    
}
