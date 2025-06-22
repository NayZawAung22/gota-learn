package org.example.gotalearn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();

    public void addRole(Role role){
        roles.add(role);
    }
}
