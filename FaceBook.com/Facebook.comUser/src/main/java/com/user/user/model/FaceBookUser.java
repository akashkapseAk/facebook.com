package com.user.user.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FaceBookUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userEmail;

    private String userGender;

    private String userCity;

    private String userAddress;

    private String visitorId;

}
