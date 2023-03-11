package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.entity.Member;

import java.util.HashMap;

public class MemberRepositoryImpl{
//
//    private final static HashMap<String, Member> DB = new HashMap<>();
//
//
//    public void createMember(MemberRequestDto memberRequestDto) {
//        Member member = new Member();
//
//        member.setId(DB.size()+1L);
//        member.setEmail(memberRequestDto.getEmail());
//        member.setUsername(memberRequestDto.getUsername());
//        member.setPassword(memberRequestDto.getPassword());
//        member.setProfileImage(memberRequestDto.getProfileImage());
////        member.setCreatedDate(LocalDateTime.now());
////        member.setUpdatedDate(LocalDateTime.now());
//    }
//
//    public Member getMember(String email) {
//        return DB.get(email);
//    }
//
//    public void updateMember(MemberRequestDto memberRequestDto) {
//        Member member = DB.get(memberRequestDto.getEmail());
//
//        member.setUsername(memberRequestDto.getUsername());
////        member.setUpdatedDate(LocalDateTime.now());
//
//        DB.replace(memberRequestDto.getEmail(), member);
//    }
//
//    public void updatePassword(String email, String password) {
//        Member member = DB.get(email);
//
//        member.setPassword(password);
////        member.setUpdatedDate(LocalDateTime.now());
//
//        DB.replace(email, member);
//    }
//
//    public void deleteMember(String email) {
//        DB.remove(email);
//    }
}
