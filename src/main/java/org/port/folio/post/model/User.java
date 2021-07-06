package org.port.folio.post.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, updatable = false, nullable = false, length = 100)
    String username;

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false, length = 50)
    String email;

    @Enumerated(EnumType.STRING)
    RoleType role;

    @CreationTimestamp
    Timestamp createdDate;

}
