package com.visitor.visitor.service;

import com.visitor.visitor.dto.FbVisitorDto;

import java.util.List;

public interface FbVisitorService {

    public FbVisitorDto createVisitor(FbVisitorDto fbVisitorDto);

    public List<FbVisitorDto> getALlVisitor();

    public FbVisitorDto getSingleVisitor(int visitorId);


}
