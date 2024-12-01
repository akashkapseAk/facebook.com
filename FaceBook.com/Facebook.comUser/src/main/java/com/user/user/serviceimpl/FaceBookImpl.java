package com.user.user.serviceimpl;

import com.user.user.dao.FaceBookUserDao;
import com.user.user.dto.FaceBookUserDto;
import com.user.user.dto.FbVisitorDto;
import com.user.user.exception.ResourceNotFoundException;
import com.user.user.model.FaceBookUser;
import com.user.user.pages.PageResponse;
import com.user.user.payload.ResponseDto;
import com.user.user.service.FaceBookUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaceBookImpl implements FaceBookUserService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FaceBookUserDao faceBookUserDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public FaceBookUserDto createUser(FaceBookUserDto faceBookUserDto) {

        FaceBookUser faceBookUser = this.modelMapper.map(faceBookUserDto, FaceBookUser.class);

        FaceBookUser bookUser = this.faceBookUserDao.save(faceBookUser);

        FaceBookUserDto bookUserDto = this.modelMapper.map(bookUser, FaceBookUserDto.class);
        return bookUserDto;
    }

    @Override
    public List<FaceBookUserDto> getAllUsers() {

        List<FaceBookUser> all = this.faceBookUserDao.findAll();
        List<FaceBookUserDto> faceBookUserDtos = all.stream().map(e -> this.modelMapper.map(e, FaceBookUserDto.class)).collect(Collectors.toList());

        return faceBookUserDtos;
    }

    @Override
    public FaceBookUserDto getSingleUser(int userId) {

        FaceBookUser faceBookUser = this.faceBookUserDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        FaceBookUserDto bookUserDto = this.modelMapper.map(faceBookUser, FaceBookUserDto.class);
        return bookUserDto;
    }

    @Override
    public FaceBookUserDto updateUser(FaceBookUserDto faceBookUserDto, int userId) {

        FaceBookUser faceBookUser = this.faceBookUserDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        faceBookUser.setUserName(faceBookUserDto.getUserName());
        faceBookUser.setUserEmail(faceBookUserDto.getUserEmail());
        faceBookUser.setUserGender(faceBookUserDto.getUserGender());
        faceBookUser.setUserCity(faceBookUserDto.getUserCity());
        faceBookUser.setUserAddress(faceBookUserDto.getUserAddress());

        FaceBookUser bookUser = this.faceBookUserDao.save(faceBookUser);
        FaceBookUserDto bookUserDto = this.modelMapper.map(bookUser, FaceBookUserDto.class);

        return bookUserDto;
    }

    @Override
    public void deleteUser(int userId) {
        FaceBookUser faceBookUser = this.faceBookUserDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));


        this.faceBookUserDao.delete(faceBookUser);
    }

    @Override
    public PageResponse getAllPages(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest of = PageRequest.of(pageNumber, pageSize, sort);

        Page<FaceBookUser> pagePost = this.faceBookUserDao.findAll(of);

        List<FaceBookUser> content = pagePost.getContent();
        List<FaceBookUserDto> faceBookUserDtos = content.stream().map(l -> this.modelMapper.map(l, FaceBookUserDto.class)).collect(Collectors.toList());

        PageResponse response=new PageResponse();

        response.setContent(faceBookUserDtos);
        response.setPageNumber(pagePost.getNumber());
        response.setPageSize(pagePost.getSize());
        response.setTotalElement(pagePost.getTotalElements());
        response.setTotalPage(pagePost.getTotalPages());
        response.setLastPage(pagePost.isLast());
        return response;
    }

    @Override
    public ResponseDto getSingleResponse(int userId) {

        FaceBookUser faceBookUser = this.faceBookUserDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        FaceBookUserDto bookUserDto = this.modelMapper.map(faceBookUser, FaceBookUserDto.class);


        FbVisitorDto fbVisitorDto = this.restTemplate.getForObject("http://FBVISITOR-SERVICE/visit/fb/s/" + faceBookUser.getVisitorId(), FbVisitorDto.class);


        ResponseDto responseDto=new ResponseDto();

        responseDto.setFaceBookUser(bookUserDto);
        responseDto.setFbVisitor(fbVisitorDto);
        return responseDto;
    }
}
