package com.user.user.dao;

import com.user.user.model.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingDao extends JpaRepository<Logging,Integer> {
    Logging findByEmail(String email);
}
