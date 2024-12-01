package com.visitor.visitor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FbVisitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitorId;

    private String visitorName;

     @CreationTimestamp
     private Date date;

    private String visitorLocation;

    private String visitorProId;

}
