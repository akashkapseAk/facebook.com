package com.user.user.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.FileSystem;
import java.util.Properties;

@Service
public class MailService {



    public boolean sendMail(String subject,String message,String to) //String fileattch
    {
        boolean f=false;
        String form="akashkapse1493@gmail.com";

        String host="smtp.gmail.com";

        Properties properties=System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","port");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akashkapse149@gmail.com","qtefmftlfuxwcpcp");
            }

        });
        session.setDebug(true);
        MimeMessage m=new MimeMessage(session);
        try {
            m.setFrom(form);
            m.addRecipients(Message.RecipientType.TO,to);
            m.setSubject(subject);
            m.setText(message);



       //attch file
//            FileSystemResource  fileSystemResource=new FileSystemResource(new File(fileattch));
//            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(m,true);
//            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
//            javaMailSender.send(m);


            Transport.send(m);
            System.out.println("sent Success");
            f=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;

    }
}
