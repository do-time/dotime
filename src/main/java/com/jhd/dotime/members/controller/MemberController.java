package com.jhd.dotime.members.controller;


import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    /*
     * JWT 도입 이후 email 파라미터 삭제 예정
     */
    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String email) {
        return new ResponseEntity<>(memberService.getMember(email), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> updateMember(@RequestBody MemberDto memberDto){
        memberService.updateMember(memberDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * JWT 도입 이후 id 파라미터 삭제 예정
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
