package com.visitor.visitor.rdao;

import com.visitor.visitor.model.FbVisitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FbVisitorDao extends JpaRepository<FbVisitor,Integer> {
}
