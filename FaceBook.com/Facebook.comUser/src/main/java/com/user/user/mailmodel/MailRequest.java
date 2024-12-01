package com.user.user.mailmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {


    private String to;
    private String message;
    private String subject;
  //   private String fileattch;
}
