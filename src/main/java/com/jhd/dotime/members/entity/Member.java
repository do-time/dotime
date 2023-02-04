package com.jhd.dotime.members.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    private Long id;

    private String email;

    private String username;

    private String password;

    private String profileImage;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
