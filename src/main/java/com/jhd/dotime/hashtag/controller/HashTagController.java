package com.jhd.dotime.hashtag.controller;


import com.jhd.dotime.hashtag.dto.HashTagRequestDto;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.service.HashTagService;
import com.jhd.dotime.hashtag.service.TaskTagService;
import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members/{memberId}/tasks/{taskId}")
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
    @ResponseBody
    @PatchMapping("/hashtag")
    public void createHashTag(@PathVariable Long taskId, @RequestBody HashTagRequestDto hashTagRequestDto){
        hashTagService.createHashtag(taskId, hashTagRequestDto);
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
