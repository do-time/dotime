package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.entity.Member;

import java.util.HashMap;

public class MemberRepositoryImpl{

    private final static HashMap<String, Member> DB = new HashMap<>();


    public void createMember(MemberDto.Request MemberDtoRequest) {
        Member member = new Member();

        member.setId(DB.size()+1L);
        member.setEmail(MemberDtoRequest.getEmail());
        member.setUsername(MemberDtoRequest.getUsername());
        member.setPassword(MemberDtoRequest.getPassword());
        member.setProfileImage(MemberDtoRequest.getProfileImage());
//        member.setCreatedDate(LocalDateTime.now());
//        member.setUpdatedDate(LocalDateTime.now());
    }

    public Member getMember(String email) {
        return DB.get(email);
    }

    public void updateMember(MemberDto.Request memberDtoRequest) {
        Member member = DB.get(memberDtoRequest.getEmail());

        member.setUsername(memberDtoRequest.getUsername());
//        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(memberDtoRequest.getEmail(), member);
    }

    public void updatePassword(String email, String password) {
        Member member = DB.get(email);

        member.setPassword(password);
//        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(email, member);
    }

    public void deleteMember(String email) {
        DB.remove(email);
    }
}
