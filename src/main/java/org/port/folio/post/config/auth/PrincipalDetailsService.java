package org.port.folio.post.config.auth;


import org.port.folio.post.model.User;
import org.port.folio.post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        
        System.out.println("유저이름: "+username);
        User user = userRepository.findByUsername(username).orElseThrow(()->{
            return new IllegalArgumentException("해당 아이디를 찾을수 없습니다.");
        });
        System.out.println("유저이름: "+user.getUsername());
        System.out.println("유저이름: "+user.getPassword());

        return new PrincipalDetails(user);
    }
    
}
