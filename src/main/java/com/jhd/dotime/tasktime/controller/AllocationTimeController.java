package com.jhd.dotime.tasktime.controller;

import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import com.jhd.dotime.tasktime.service.AllocationTimeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            value = "Task 별 TimeLog 조회",
            notes = "TaskId에 해당하는 TimeLog 들을 조회한다."
    )
    @PostMapping
    public ResponseEntity<?> insertAllocationTime(@RequestBody AllocationTimeDto.Request requestDto){
        allocationTimeService.insertAllocationTime(requestDto);
        return null;
    }
}
