package com.user.user.mailController;

import com.user.user.mailmodel.MailRequest;
import com.user.user.mailservice.MailService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
@CrossOrigin("*")
public class MailReqController {


    @Autowired
    private MailService mailService;

    private static final Log log= LogFactory.getLog(MailReqController.class);

    @PostMapping("/")
    public ResponseEntity<?> sendMail(@RequestBody MailRequest request)
    {
        System.out.println(request);

      try {

          log.info("Enter the sendmail from MailReqController:{}"+request);
          boolean restult = this.mailService.sendMail(request.getSubject(), request.getMessage(), request.getTo());
          log.info("exit  the sendmail from MailReqController:{}"+request);
          return new ResponseEntity<>(restult, HttpStatus.OK);

      }catch (Exception e)
      {
          log.error("error occurred in sendMail from MailReqControoler :{}"+e.getMessage()+request);
      }

        log.info("checking error  the sendmail from MailReqController:{}"+request);
        boolean restult = this.mailService.sendMail(request.getSubject(), request.getMessage(), request.getTo());
        log.info("exit checking error  the sendmail from MailReqController:{}"+request);
      return new ResponseEntity<>(restult,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
