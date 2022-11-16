package com.ssd.file;

import com.ssd.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "file")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileID;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "content")
    @Lob
    private byte[] content;

    @Column(name = "hased")
    private String hashed;
}
