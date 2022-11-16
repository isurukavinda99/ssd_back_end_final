package com.ssd.file;

import com.ssd.Hashing.Hashing;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/file")
public class FileController {

    private final FileService fileService;

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<FileEntity> upload(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity entity = new FileEntity();
        entity.setFileName(file.getName());
        entity.setContent(file.getBytes());
        entity.setHashed(Hashing.encrypt(file.getName()+file.getBytes()));
        return new ResponseEntity<>(fileService.uploadFile(entity), HttpStatus.ACCEPTED);
    }

}
