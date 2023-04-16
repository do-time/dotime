package com.jhd.dotime.tasktime.controller;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.service.TaskTimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/task-time-logs")
@RequiredArgsConstructor
public class TaskTimeLogController {

    private final TaskTimeLogService taskTimeLogService;

    @PostMapping("/start")
    public ResponseEntity<TaskTimeLogDto.Response> startTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto) {
        // Task가 사용자의 Task인지 인증 로직 필요
        // >> 공통으로 빼놓아도 좋을듯

        return new ResponseEntity<>(taskTimeLogService.startTimeLog(requestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/pause")
    public ResponseEntity<TaskTimeLogDto.Response> pauseTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto){
        return new ResponseEntity<>(taskTimeLogService.pauseTimeLog(requestDto), HttpStatus.OK);
    }

    @PatchMapping("/end")
    public ResponseEntity<TaskTimeLogDto.Response> endTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto){
        return new ResponseEntity<>(taskTimeLogService.endTimeLog(requestDto), HttpStatus.OK);
    }
}
