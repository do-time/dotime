package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;

public class MemberRepositoryImpl{

    private final static HashMap<String, Member> DB = new HashMap<>();


    public void createMember(MemberDto memberDto) {
        Member member = new Member();

        member.setId(DB.size()+1L);
        member.setEmail(memberDto.getEmail());
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setProfileImage(memberDto.getProfileImage());
        member.setCreatedDate(LocalDateTime.now());
        member.setUpdatedDate(LocalDateTime.now());
    }

    public Member getMember(String email) {
        return DB.get(email);
    }

    public void updateMember(MemberDto memberDto) {
        Member member = DB.get(memberDto.getEmail());

        member.setUsername(memberDto.getUsername());
        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(memberDto.getEmail(), member);
    }

    public void updatePassword(String email, String password) {
        Member member = DB.get(email);

        member.setPassword(password);
        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(email, member);
    }

    public void deleteMember(String email) {
        DB.remove(email);
    }
}
