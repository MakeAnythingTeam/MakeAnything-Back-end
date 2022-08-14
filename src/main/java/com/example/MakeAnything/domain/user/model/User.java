package com.example.MakeAnything.domain.user.model;

import com.example.MakeAnything.domain.auth.service.dto.SignUpLocalRequest;
import com.example.MakeAnything.domain.auth.service.dto.SignUpSocialRequest;
import com.example.MakeAnything.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;

    private String userName;

    private String email;

    private String nickName;

    private String password;

    private String phoneNumber;

    private String address;

    @Builder(access = AccessLevel.PRIVATE)
    public User(String socialId, String userName, String email, String nickName, String password, String phoneNumber, String address) {
        this.socialId = socialId;
        this.userName = userName;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static User newLocalInstance(SignUpLocalRequest request, String encryptPassword) {
        return User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .nickName(request.getNickName())
                .password(encryptPassword)
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }

    public static User newSocialInstance(SignUpSocialRequest request, String socialId, String email) {
        return User.builder()
                .userName(request.getUserName())
                .socialId(socialId)
                .email(email)
                .nickName(request.getNickName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
