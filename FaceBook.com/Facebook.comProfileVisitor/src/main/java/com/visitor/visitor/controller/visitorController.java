package com.visitor.visitor.controller;

import com.visitor.visitor.dto.FbVisitorDto;
import com.visitor.visitor.service.FbVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit/fb")
public class visitorController {

    @Autowired
    private FbVisitorService fbVisitorService;

    @PostMapping("/")
    public ResponseEntity<FbVisitorDto> createVisitor(@RequestBody FbVisitorDto fbVisitorDto)
    {
        FbVisitorDto visitor = this.fbVisitorService.createVisitor(fbVisitorDto);
        return new ResponseEntity<>(visitor, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FbVisitorDto>> getAllVisit()
    {
        List<FbVisitorDto> aLlVisitor = this.fbVisitorService.getALlVisitor();

        return new ResponseEntity<>(aLlVisitor,HttpStatus.OK);
    }

    @GetMapping("/s/{visitorId}")
    public ResponseEntity<FbVisitorDto> getSingleVisit(@PathVariable("visitorId") int visitorId)
    {
        FbVisitorDto singleVisitor = this.fbVisitorService.getSingleVisitor(visitorId);

        return new ResponseEntity<>(singleVisitor,HttpStatus.OK);
    }
}
