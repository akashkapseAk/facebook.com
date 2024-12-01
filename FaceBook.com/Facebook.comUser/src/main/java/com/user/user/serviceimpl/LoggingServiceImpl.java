package com.user.user.serviceimpl;

import com.user.user.dao.LoggingDao;
import com.user.user.model.Logging;
import com.user.user.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {


    @Autowired
    private LoggingDao loggingDao;


    @Override
    public Logging createLog(Logging logging) {

        Logging logging1 = this.loggingDao.save(logging);
        return logging1;
    }
}
