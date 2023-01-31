package com.example.MakeAnything.domain.user.model;

import com.example.MakeAnything.domain.auth.model.AuthProvider;
import com.example.MakeAnything.domain.auth.model.UserRole;
import com.example.MakeAnything.domain.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String nickName;

    private String password;

    private String phoneNumber;

    private String address;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
}
