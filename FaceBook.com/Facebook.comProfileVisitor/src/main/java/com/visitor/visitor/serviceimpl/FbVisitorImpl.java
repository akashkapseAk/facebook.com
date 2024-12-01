package com.visitor.visitor.serviceimpl;

import com.visitor.visitor.dto.FbVisitorDto;
import com.visitor.visitor.exception.ResourceNotFoundException;
import com.visitor.visitor.model.FbVisitor;
import com.visitor.visitor.rdao.FbVisitorDao;
import com.visitor.visitor.service.FbVisitorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FbVisitorImpl implements FbVisitorService {

    @Autowired
    private FbVisitorDao fbVisitorDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FbVisitorDto createVisitor(FbVisitorDto fbVisitorDto) {

        FbVisitor fbVisitor = this.modelMapper.map(fbVisitorDto, FbVisitor.class);
        FbVisitor visitor = this.fbVisitorDao.save(fbVisitor);

        FbVisitorDto visitorDto = this.modelMapper.map(visitor, FbVisitorDto.class);
        return visitorDto;
    }

    @Override
    public List<FbVisitorDto> getALlVisitor() {

        List<FbVisitor> all = this.fbVisitorDao.findAll();
        List<FbVisitorDto> fbVisitorDtos = all.stream().map(e -> this.modelMapper.map(e, FbVisitorDto.class)).collect(Collectors.toList());
        return fbVisitorDtos;
    }

    @Override
    public FbVisitorDto getSingleVisitor(int visitorId) {

        FbVisitor fbVisitor = this.fbVisitorDao.findById(visitorId).orElseThrow(() -> new ResourceNotFoundException("Visitor", "visitorId", visitorId));

        FbVisitorDto visitorDto = this.modelMapper.map(fbVisitor, FbVisitorDto.class);
        return visitorDto;
    }
}
