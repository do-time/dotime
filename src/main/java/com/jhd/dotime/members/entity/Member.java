package com.jhd.dotime.members.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.dto.Role;
import lombok.*;

import javax.persistence.*;

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

    /*
     * @Enumerated
     * EnumType.STRING  : 각 Enum 이름을 DB 컬럼에 저장
     * EnumType.ORDINAL : 각 Enum에 대응되는 순서를 컬럼에 저장
     */
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public void updateInfo(String username){
        this.username = username;
    }

//    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_id")
//    private List<Task> task = new ArrayList<>();
}
