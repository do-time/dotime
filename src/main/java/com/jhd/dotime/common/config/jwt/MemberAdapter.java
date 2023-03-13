package com.jhd.dotime.common.config.jwt;

import com.jhd.dotime.auth.entity.Auth;
import com.jhd.dotime.members.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberAdapter extends User {
    private Member member;

    public MemberAdapter(Member member) {
        super(member.getEmail(), member.getPassword(), authorities(member.getAuthorities()));
        this.member = member;
    }

    public Member getMember() {
        return this.member;
    }

    private static List<GrantedAuthority> authorities(Set<Auth> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
    }
}