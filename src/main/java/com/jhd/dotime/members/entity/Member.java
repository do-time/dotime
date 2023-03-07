package com.jhd.dotime.members.entity;

import com.jhd.dotime.common.entity.Authority;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "activated")
    private boolean activated;

    public void updateInfo(String username){
        this.username = username;
    }

//    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_id")
//    private List<Task> task = new ArrayList<>();

    @ManyToMany
    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public Member(String email, String username, String password, Set<Authority> authorities, boolean activated) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.activated = activated;
//        this.tokenWeight = 1L;
    }
}
