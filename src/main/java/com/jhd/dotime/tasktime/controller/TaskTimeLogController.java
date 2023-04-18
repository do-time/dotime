package com.jhd.dotime.tasktime.controller;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.service.TaskTimeLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task-time-logs")
@RequiredArgsConstructor
public class TaskTimeLogController {

    private final TaskTimeLogService taskTimeLogService;
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Task 별 TimeLog 조회",
            notes = "TaskId에 해당하는 TimeLog 들을 조회한다."
    )
    @GetMapping("/{id}")

    public ResponseEntity<List<TaskTimeLogDto.Response>> getTimeLog(@CurrentMember Member member, @PathVariable Long id) {
        return new ResponseEntity<>(taskTimeLogService.getTimeLog(id), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Member 별 TimeLog 조회",
            notes = "MemberId에 해당하는 TimeLog 들을 조회한다."
    )
    @GetMapping
    public ResponseEntity<List<TaskTimeLogDto.Response>> getTimeLogByMember(@CurrentMember Member member) {
        return new ResponseEntity<>(taskTimeLogService.getTimeLogByMember(member.getId()), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Task TimeLog 시작",
            notes = "Task 시작시 로그를 생성한다.(Task 시작과 Pause 후 재개할 때도 이 API를 사용한다.)"
    )
    @PostMapping("/start")
    public ResponseEntity<TaskTimeLogDto.Response> startTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto) {
        // Task가 사용자의 Task인지 인증 로직 필요
        // >> 공통으로 빼놓아도 좋을듯

        return new ResponseEntity<>(taskTimeLogService.startTimeLog(requestDto), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Task TimeLog 정지",
            notes = "Task 일시 정지시 EndTime을 지정한다."
    )
    @PatchMapping("/pause")
    public ResponseEntity<TaskTimeLogDto.Response> pauseTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto){
        return new ResponseEntity<>(taskTimeLogService.pauseTimeLog(requestDto), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Task TimeLog 종료",
            notes = "Task 종료시 EndTime을 지정한다."
    )
    @PatchMapping("/end")
    public ResponseEntity<TaskTimeLogDto.Response> endTimeLog(@CurrentMember Member member, @RequestBody TaskTimeLogDto.Request requestDto){
        return new ResponseEntity<>(taskTimeLogService.endTimeLog(requestDto), HttpStatus.OK);
    }
}
