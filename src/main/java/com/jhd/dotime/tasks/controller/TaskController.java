package com.jhd.dotime.tasks.controller;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.hashtag.service.HashTagService;
import com.jhd.dotime.hashtag.service.TaskTagService;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskDto;


import com.jhd.dotime.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "tasks", description = "Task API")

@RestController @RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskTagService taskTagService;

    private final HashTagService hashTagService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TaskDto.Response.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @Parameters({
            @Parameter(name="task id")
    })
    @GetMapping("/task/{id}")
    public ResponseEntity<List<TaskDto.Response>> getTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.findTask(id), HttpStatus.OK);
    }



    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @GetMapping("/task")
    public ResponseEntity<List<TaskDto.Response>> getTask(){
        return new ResponseEntity<>(taskService.findTaskList(), HttpStatus.OK);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.delete(id), HttpStatus.NO_CONTENT);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/task/{id}")
    public ResponseEntity<Long> updateTask(@PathVariable Long id, @RequestBody TaskDto.Request taskRequestDto, @CurrentMember Member member){
        return new ResponseEntity<>(taskService.update(id, taskRequestDto, hashTagService.createHashtag(taskRequestDto.getHashtag())), HttpStatus.OK);

    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/task")
    public ResponseEntity<Long> saveTask(@RequestBody TaskDto.Request taskRequestDto, @CurrentMember Member member){
        return new ResponseEntity<>(taskTagService.createTaskTag(taskService.insert(member.getId(), taskRequestDto), hashTagService.createHashtag(taskRequestDto.getHashtag())), HttpStatus.CREATED);

    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @GetMapping("/taskList")
    public ResponseEntity<List<TaskDto.Response>> getTaskList(@CurrentMember Member member){
        return new ResponseEntity<>(taskService.getTaskListByMemberId(member.getId()), HttpStatus.OK);
    }

}
