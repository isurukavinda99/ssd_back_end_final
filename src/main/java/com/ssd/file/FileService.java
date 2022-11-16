package com.ssd.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public FileEntity uploadFile(FileEntity fileEntity) {

        return fileRepository.save(fileEntity);
    }

}
