package com.visitor.visitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FbVisitorDto {


    private int visitorId;

    private String visitorName;

    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date;

    private String visitorLocation;

    private String visitorProId;
}
