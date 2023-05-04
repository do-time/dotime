package com.jhd.dotime.tasktime.controller;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import com.jhd.dotime.tasktime.service.AllocationTimeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("allocation-time")
@RequiredArgsConstructor
public class AllocationTimeController {
    private final AllocationTimeService allocationTimeService;
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Allocation Time 입력",
            notes = "TaskId의 할당 시간을 구분 별로 입력 받아 저장한다"
    )
    @PostMapping
    public ResponseEntity<Void> insertAllocationTime(@CurrentMember Member member, @RequestBody AllocationTimeDto.Request requestDto) {
        allocationTimeService.insertAllocationTime(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Allocation Time 수정",
            notes = "TaskId의 할당 시간을 구분 별로 수정한다."
    )
    @PatchMapping
    public ResponseEntity<Void> updateAllocationTime(@CurrentMember Member member, @RequestBody AllocationTimeDto.Request requestDto){
        allocationTimeService.updateAllocationTime(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ApiOperation(
            value = "Allocation Time 조회",
            notes = "TaskId의 할당 시간 리스트를 조회한다."
    )
    @GetMapping("/{taskId}")
    public ResponseEntity<List<AllocationTimeDto.Response>> getAllocationTime(@CurrentMember Member member, @PathVariable Long taskId){
        return new ResponseEntity<>(allocationTimeService.getAllocationTime(taskId), HttpStatus.OK);
    }
}
