package com.user.user.controller;

import com.user.user.dto.FaceBookUserDto;
import com.user.user.exception.ApiResponse;
import com.user.user.model.FaceBookUser;
import com.user.user.pages.AppConstant;
import com.user.user.pages.PageResponse;
import com.user.user.payload.ResponseDto;
import com.user.user.service.FaceBookUserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/fb")
@CrossOrigin("*")
public class FbUserController {

    @Autowired
    private FaceBookUserService faceBookUserService;

    private static final Logger log= LoggerFactory.getLogger(FbUserController.class);


    @PostMapping("/")
    public ResponseEntity<FaceBookUserDto> createUser(@RequestBody FaceBookUserDto faceBookUserDto)
    {
        try {
            FaceBookUserDto user = this.faceBookUserService.createUser(faceBookUserDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception ex)
        {
            log.error("error occurred in Create userfrom FbController class{}"+ex.getMessage(),faceBookUserDto);
        }

        FaceBookUserDto user = this.faceBookUserService.createUser(faceBookUserDto);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FaceBookUserDto>> getAllUser()
    {
        try {

            List<FaceBookUserDto> allUsers = this.faceBookUserService.getAllUsers();
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }catch (Exception ex)
        {
            log.error("error occurred in get all user from FbController class{}"+ex.getMessage());
        }
        List<FaceBookUserDto> allUsers = this.faceBookUserService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.NO_CONTENT);

    }

    @GetMapping("/s/{userId}")
    public ResponseEntity<FaceBookUserDto> getSingleUser(@PathVariable("userId") int userId)
    {
        try {
            FaceBookUserDto singleUser = this.faceBookUserService.getSingleUser(userId);

            return new ResponseEntity<>(singleUser,HttpStatus.OK);

        }catch (Exception ex)
        {
            log.error("error occurred in get single user from FbController class{}"+ex.getMessage());
        }

        FaceBookUserDto singleUser = this.faceBookUserService.getSingleUser(userId);

        return new ResponseEntity<>(singleUser,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/up/{userId}")
    public ResponseEntity<FaceBookUserDto> updateUser(@RequestBody FaceBookUserDto faceBookUserDto,@PathVariable("userId") int userId)
    {
        try {

            FaceBookUserDto updatedUser = this.faceBookUserService.updateUser(faceBookUserDto, userId);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);

        }catch (Exception ex)
        {
            log.error("error occurred in get single user from FbController class{}"+ex.getMessage(),userId);
        }
        FaceBookUserDto updatedUser = this.faceBookUserService.updateUser(faceBookUserDto, userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/d/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId)
    {
        try {

            this.faceBookUserService.deleteUser(userId);
            return new ResponseEntity<>(new ApiResponse("User delete successfully",true),HttpStatus.OK);

        }catch (Exception ex)
        {

            log.error("error occurred in update user from FbController class{}"+ex.getMessage(),userId);
        }

        this.faceBookUserService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("internal server error",true),HttpStatus.OK);
    }


    @GetMapping("/all/pages")
    public ResponseEntity<PageResponse> getALlPages(
            @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) int pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
    )
    {
        PageResponse allPages = this.faceBookUserService.getAllPages(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(allPages,HttpStatus.OK);
    }

    @GetMapping("/s/rep/{userId}")
    @CircuitBreaker(name = "FbVisitorBreaker",fallbackMethod = "FbVisitorBreakerMethod")
    public ResponseEntity<ResponseDto> getSinglePage(@PathVariable("userId") int userId)
    {
        ResponseDto singleResponse = this.faceBookUserService.getSingleResponse(userId);

        return new ResponseEntity<>(singleResponse,HttpStatus.OK);
    }

    public ResponseEntity<FaceBookUser> FbVisitorBreakerMethod(int userId,Exception ex)
    {
        FaceBookUser faceBookUser= FaceBookUser.builder().userId(12).userName("xyz")
                .userEmail("dummy@gmail.com").userGender("dummy").userCity("dummy").userAddress("dummy")
                .visitorId("12345").build();

        return new ResponseEntity<>(faceBookUser,HttpStatus.OK);
    }
}
