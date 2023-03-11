package com.jhd.dotime.members.entity;

import com.jhd.dotime.auth.entity.Auth;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.dto.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
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

    /*
     * @Enumerated
     * EnumType.STRING  : 각 Enum 이름을 DB 컬럼에 저장
     * EnumType.ORDINAL : 각 Enum에 대응되는 순서를 컬럼에 저장
     */
//    @Enumerated(value = EnumType.STRING)
//    private Role role;

    @ManyToMany
    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Auth> authorities;

    public void updateInfo(String username){
        this.username = username;
    }


}
