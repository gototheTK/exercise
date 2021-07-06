package org.port.folio.post.repository;

import java.util.Optional;

import org.port.folio.post.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{


    Optional<User> findByUsername(String username);
    
}
