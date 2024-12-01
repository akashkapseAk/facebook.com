package com.user.user.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FileController {

    @Autowired
    private FileDao fileDao;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFilr(@RequestParam("file") MultipartFile file)
    {
        try {

            FIleEntity fIleEntity = new FIleEntity();

            fIleEntity.setFileName(file.getOriginalFilename());
            fIleEntity.setContentType(file.getContentType());
            fIleEntity.setData(file.getBytes());

            fileDao.save(fIleEntity);

            String message = "file upload succeefully";

            HttpStatus httpStatus = HttpStatus.CREATED;

            return new ResponseEntity<>(message, httpStatus);

        }catch (IOException e)
        {
            return ResponseEntity.status(500).build();
        }


    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downLoadFile(@PathVariable long id)
    {
        FIleEntity fIleEntity=fileDao.findById(id).orElse(null);
        if (fIleEntity!=null)
        {
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(fIleEntity.getContentType()));
            headers.setContentDisposition(ContentDisposition.attachment().filename(fIleEntity.getFileName()).build());

            ByteArrayResource resource=new ByteArrayResource(fIleEntity.getData());

            return ResponseEntity.ok().headers(headers).body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
