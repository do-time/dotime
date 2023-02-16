package com.jhd.dotime.members.controller;


import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
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
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<Void> createMember(@RequestBody MemberDto memberDto) {
        memberService.createMember(memberDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String email) {
        return new ResponseEntity<MemberResponseDto>(memberService.getMember(email).map(MemberResponseDto::new).orElseThrow(() -> new NotFoundException("Member does not exist")), HttpStatus.OK);
    }

}
