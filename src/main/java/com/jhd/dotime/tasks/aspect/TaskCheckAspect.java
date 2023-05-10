package com.jhd.dotime.tasks.aspect;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TaskCheckAspect {
    private final TaskService taskService;

    /**
     * Controller 직전에 Task 존재 여부 및 접근 권한을 체크하는 공통 로직
     * @methodname   : taskCheck
     * @author       : jihoon
     * @date         : 2023-05-10
     * @param        : member, taskId
     * @description  : args는 기본적으로 파라미터 포인트 컷(AOP 적용 조건)에 해당하므로 해당 타입이 순서대로 매칭되지 않으면 AOP가 적용되지 않는다.
     *                 @TaskCheck 어노테이션으로 사용 가능
    **/
    @Before("@annotation(com.jhd.dotime.tasks.aspect.TaskCheck) && args(member, taskId)")
    public void taskCheck(JoinPoint joinPoint, Member member, Long taskId){
        // Task 존재여부 판별
        if(!taskService.existTask(taskId) == false){
            throw new TaskException(TaskErrorCode.TASK_NOT_FOUNT);
        }

        // Task 접근 권한 확인
        if(!taskService.existTask(member.getId(), taskId)){
            throw new TaskException(TaskErrorCode.UNAUTHORIZED_TASK);
        }
    }
}
