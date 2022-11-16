package com.ssd.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "tbl_role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String role;
    private String description;
}
