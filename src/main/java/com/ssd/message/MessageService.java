package com.ssd.message;

import com.ssd.Hashing.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageEntity post(MessageEntity messageEntity){
        messageEntity.setHashed(Hashing.encrypt(messageEntity.getMsgTitle()+messageEntity.getContent()));
        return messageRepository.save(messageEntity);
    }
}
