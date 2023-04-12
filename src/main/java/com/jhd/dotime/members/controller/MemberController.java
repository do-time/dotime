package com.jhd.dotime.members.controller;


import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.dto.MemberDto.Response;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.service.MemberService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping()
    public ResponseEntity<Void> createMember(@RequestBody MemberDto.Request memberDtoRequest) {
        memberService.createMember(memberDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
     * JWT 도입 이후 email 파라미터 삭제 예정
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping()
    public ResponseEntity<MemberDto.Response> getMember(@CurrentMember Member member) {
        return new ResponseEntity<>(memberService.getMember(member.getEmail()), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping()
    public ResponseEntity<Void> updateMember(@RequestBody MemberDto.Request memberDtoRequest, @CurrentMember Member member){
        memberService.updateMember(memberDtoRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * JWT 도입 이후 id 파라미터 삭제 예정
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
    })
    @DeleteMapping()
    public ResponseEntity<Void> deleteMember(@CurrentMember Member member){
        memberService.deleteMember(member.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
