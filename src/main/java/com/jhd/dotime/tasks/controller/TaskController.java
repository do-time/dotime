package com.jhd.dotime.tasks.controller;

import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.dto.TaskUpdateRequestDto;
import com.jhd.dotime.tasks.entity.Task;
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


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TaskResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @ResponseBody
    @Parameters({
            @Parameter(name="task id")
    })
    @GetMapping("/task/{id}")
    public TaskResponseDto getTask(@PathVariable Long id){
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
    public List<TaskResponseDto> getTask(){
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
    public Long updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequestDto taskUpdateRequestDto){
        return taskService.update(id, taskUpdateRequestDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/task")
    public Long saveTask(@RequestBody TaskSaveRequestDto taskSaveRequestDto){
        return taskService.insert(taskSaveRequestDto);
    }

}
