package com.jhd.dotime.members.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
public class Member {
    @Id
    private Long id;

    private String email;

    private String username;

    private String password;

    private String profileImage;

    private Date createdDate;

    private Date updatedDate;
}
