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
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "tasks", description = "Task API")

@RestController @RequiredArgsConstructor
@RequestMapping("api/v1")
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
    public List<TaskDto.Response> getTask(@PathVariable Long id){
        return taskService.findTask(id);
    }



    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @GetMapping("/task")
    public List<TaskDto.Response> getTask(){
        return taskService.findTaskList();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/task/{id}")
    public Long deleteTask(@PathVariable Long id){
        return taskService.delete(id);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/task/{id}")
    public Long updateTask(@PathVariable Long id, @RequestBody TaskDto.Request taskRequestDto, @CurrentMember Member member){
        return taskService.update(id, taskRequestDto);

    }
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
//    @PatchMapping("members/{memberId}/task/{id}")
//    public ResponseEntity<Long> updateTask(@PathVariable Long memberId, @PathVariable Long id, @RequestBody TaskUpdateRequestDto taskUpdateRequestDto){
////        return new ResponseEntity<>(taskService.update(id, taskUpdateRequestDto), HttpStatus.CREATED);
//
//    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/task")
    public Long saveTask(@RequestBody TaskDto.Request taskRequestDto, @CurrentMember Member member){
        return taskTagService.createTaskTag(taskService.insert(member.getId(), taskRequestDto), hashTagService.createHashtag(taskRequestDto.getHashtag()));

    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @GetMapping("/taskList")
    public List<TaskDto.Response> getTaskList(@CurrentMember Member member){
        return taskService.getTaskListByMemberId(member.getId());
    }

}
