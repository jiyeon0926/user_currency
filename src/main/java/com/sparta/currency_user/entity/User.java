package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private List<Exchange> exchanges = new ArrayList<>();

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}