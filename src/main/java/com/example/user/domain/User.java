package com.example.user.domain;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    // @Enumerated(EnumType.STRING)
    @Column
    private String role;

    // @CreationTimestamp
    // private Timestamp createDate;
    @JoinColumn(name = "region_id")
    @OneToOne
    private Regions region;

    @Builder
    public User(String userName, String password, String role, Regions region) {
        this.username = userName;
        this.password = password;
        this.role = role;
        this.region = region;
    }
}
