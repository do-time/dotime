package com.jhd.dotime.members.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.tasks.entity.Task;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String username;

    private String password;

    @Column(nullable = true)
    private String profileImage;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<Task> task = new ArrayList<>();
}
