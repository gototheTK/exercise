package org.port.folio.post.service;

import org.port.folio.post.model.RoleType;
import org.port.folio.post.model.User;
import org.port.folio.post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encodePW;

    @Transactional
    public void 회원가입(User user){

        String rawPassword = user.getPassword();
        String encPassword = encodePW.encode(rawPassword);
        
        System.out.println("비밀번호: " + rawPassword);
        System.out.println("비밀번호: " + encPassword);
        user.setRole(RoleType.USER);
        user.setPassword(encPassword);

        userRepository.save(user);
    }


    @Transactional
    public void 회원정보수정(User user){

        User entity = userRepository.findByUsername(user.getUsername()).orElseThrow(()->{return new IllegalArgumentException("아이디를 찾을수 없습니다");});




        String rawPassword = user.getPassword();
        String encPassword = encodePW.encode(rawPassword);
        
        entity.setEmail(user.getEmail());
        entity.setPassword(encPassword);

        userRepository.save(entity);
    }

    
}
