package com.jhd.dotime.members.vo;

import com.jhd.dotime.auth.entity.Authority;
import com.jhd.dotime.members.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MemberAdapter extends User {
    private Member member;

    public MemberAdapter(Member member) {
        super(member.getEmail(), member.getPassword(), authorities(member.getAuthorities()));
        this.member = member;
    }
    private static List<GrantedAuthority> authorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
    }
}
