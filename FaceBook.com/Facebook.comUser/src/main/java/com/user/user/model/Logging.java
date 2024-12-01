package com.user.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Logging {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int logId;

    private String name;

    private String email;

    private String password;

}
