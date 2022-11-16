package com.ssd.message;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/msg")
public class MessageController {

    private final MessageService messageService;

    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    @PostMapping("/post")
    public ResponseEntity<MessageEntity> postMessage(@RequestBody MessageEntity messageEntity){
        if(messageEntity == null){
            throw new RuntimeException("bad request");
        }else if(messageEntity.getMsgTitle().equals("") || messageEntity.getMsgTitle() == null){
            throw new RuntimeException("message title required");
        }else if(messageEntity.getContent().equals("") || messageEntity.getContent() == null){
            throw new RuntimeException("message required");
        }else{
            return new ResponseEntity<>(messageService.post(messageEntity), HttpStatus.CREATED);
        }
    }
}
