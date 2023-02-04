package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class MemberRepositoryImpl implements MemberRepository{

    private final static HashMap<String, Member> DB = new HashMap<>();


    @Override
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

    @Override
    public Member getMember(String email) {
        return DB.get(email);
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        Member member = DB.get(memberDto.getEmail());

        member.setUsername(memberDto.getUsername());
        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(memberDto.getEmail(), member);
    }

    @Override
    public void updatePassword(String email, String password) {
        Member member = DB.get(email);

        member.setPassword(password);
        member.setUpdatedDate(LocalDateTime.now());

        DB.replace(email, member);
    }

    @Override
    public void deleteMember(String email) {
        DB.remove(email);
    }
}
