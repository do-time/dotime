package com.jhd.dotime.tasktimelog.controller;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasktimelog.service.TaskTimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/logs")
@RequiredArgsConstructor
public class TaskTimeLogController {

    private TaskTimeLogService taskTimeLogService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Void> startTimeLog(@CurrentMember Member member) {
//        // Task가 사용자의 Task인지 인증 로직 필요
//        // >> 공통으로 빼놓아도 좋을듯
//
//        //taskTimeLogService.startTimeLog(id);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
