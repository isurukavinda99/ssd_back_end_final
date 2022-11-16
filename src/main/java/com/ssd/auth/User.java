package com.ssd.auth;

import com.ssd.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username" , unique = true)
    private String username;
    @Column(name = "first_name" , length = 30 , nullable = false)
    private String firstName;
    @Column(name = "last_name" , length = 30 , nullable = false)
    private String lastName;
    @Column(name = "email" , nullable = false , unique = true)
    private String email;
    @Column(name = "password" , nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_user_role" , joinColumns = {
            @JoinColumn(name = "user_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    })

    private Set<Role> role;

}
