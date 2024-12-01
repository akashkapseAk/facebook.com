package com.user.user.controller;

import com.user.user.dao.LoggingDao;
import com.user.user.model.Logging;
import com.user.user.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoggingController {

    @Autowired
    private LoggingDao loggingDao;
    @Autowired
    private LoggingService loggingService;

    @PostMapping("/")
    public ResponseEntity<Logging> createLogUser(@RequestBody Logging logging)
    {
        Logging log = this.loggingService.createLog(logging);
        return new ResponseEntity<>(log, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Logging> login(@RequestBody Logging loggingData)
    {
        Logging logging = this.loggingDao.findByEmail(loggingData.getEmail());

        if (logging.getPassword().equals(loggingData.getPassword()))
            return ResponseEntity.ok(logging);

        return new ResponseEntity<>(logging,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
