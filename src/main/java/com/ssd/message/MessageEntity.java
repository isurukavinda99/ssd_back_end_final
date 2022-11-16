package com.ssd.message;

import com.ssd.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "message")
public class MessageEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long msgId;

    @Column(name = "msg_title")
    private String msgTitle;

    @Column(name="content")
    private String content;

    @Column(name = "hashed")
    private String hashed;
}
