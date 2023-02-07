package com.jhd.dotime.members.controller;


import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jhd.dotime.common.ApiUtils.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Void> createMember(@RequestBody MemberDto memberDto) {
        memberService.createMember(memberDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Member> getMember(@PathVariable String email) {
        return new ResponseEntity<Member>(memberService.getMember(email).orElseThrow(() -> new NotFoundException("Member does not exist")), HttpStatus.OK);
    }
}
