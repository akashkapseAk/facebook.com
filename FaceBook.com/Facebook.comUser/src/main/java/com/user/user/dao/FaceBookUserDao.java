package com.user.user.dao;

import com.user.user.model.FaceBookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceBookUserDao extends JpaRepository<FaceBookUser,Integer> {
}
