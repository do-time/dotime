package com.jhd.dotime.hashtag.controller;


import com.jhd.dotime.hashtag.dto.HashTagDto;
import com.jhd.dotime.hashtag.dto.HashTagDto.Request;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.service.HashTagService;
import com.jhd.dotime.hashtag.service.TaskTagService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Test 용 Controller
 */
@RestController
@RequiredArgsConstructor
public class HashTagController {

    private final HashTagService hashTagService;

    private final TaskTagService taskTagService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/hashtag")
    public List<HashTag> getHashTagList(){
        return hashTagService.getHashTagList();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/tasktag")
    public List<HashTag> getTaskTagList(@PathVariable Long taskId){
        return taskTagService.getHashTagList(taskId);
    }


}
