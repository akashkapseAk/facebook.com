package com.user.user.service;

import com.user.user.dto.FaceBookUserDto;
import com.user.user.pages.PageResponse;
import com.user.user.payload.ResponseDto;

import java.util.List;

public interface FaceBookUserService {


    public FaceBookUserDto createUser(FaceBookUserDto faceBookUserDto);

    public List<FaceBookUserDto> getAllUsers();

    public FaceBookUserDto getSingleUser(int userId);

    public FaceBookUserDto updateUser(FaceBookUserDto faceBookUserDto,int userId);

    public void deleteUser(int userId);

    public PageResponse getAllPages(int pageNumber,int pageSize,String sortBy,String sortDir);

    public ResponseDto getSingleResponse(int userId);
}
