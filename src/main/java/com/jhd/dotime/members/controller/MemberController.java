package com.jhd.dotime.members.controller;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.jhd.dotime.common.ApiUtils.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{email}")
    public ApiResult<Member> getMember(@PathVariable String email){
        return success(memberService.getMember(email));
    }
}
