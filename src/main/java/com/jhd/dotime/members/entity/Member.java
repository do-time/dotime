package com.jhd.dotime.members.entity;

import com.jhd.dotime.auth.entity.Authority;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import io.jsonwebtoken.Claims;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public Member(String email, String username, String password, String profileImage, Set<Authority> authorities, boolean activated) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
        this.authorities = authorities;
        this.activated = activated;
//        this.tokenWeight = 1L;
    }

    public Member(Claims claims){

    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Member))
            return false;

        Member member = (Member)o;

        return member.id == this.id &&
                member.email.equals(this.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, email);
    }
}
