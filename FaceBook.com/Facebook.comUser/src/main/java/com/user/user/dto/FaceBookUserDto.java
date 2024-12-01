package com.user.user.dto;

import lombok.*;

import javax.persistence.Column;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceBookUserDto {


    private int userId;


    private String userName;

    private String userEmail;

    private String userGender;

    private String userCity;

    private String userAddress;

    private String visitorId;
}
