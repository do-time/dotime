package com.jhd.dotime.tasks.aspect;

import com.jhd.dotime.common.annotation.CurrentMember;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TaskCheckAspect {
    private static final String MEMBER_PARAM = "member";
    private final TaskService taskService;

    @Before("@annotation(com.jhd.dotime.tasks.aspect.TaskCheck)")
    public void taskCheck(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs(); // 메서드의 파라미터의 값 배열을 꺼내옵니다.
        String[] parameterNames = signature.getParameterNames(); // 메서드의 파라미터들의 이름 배열을 꺼내옵니다.
        Class<?>[] parameterTypes = signature.getParameterTypes(); // 메서드의 파라미터들의 타입 배열을 꺼내옵니다.

        Member currentMember = getCurrentMember(args, parameterNames, parameterTypes);

    }

    private Member getCurrentMember(Object[] args, String[] parameterNames, Class[] parameterTypes) {
        Member member = null;

        // 파라미터들의 배열, 즉 3개의 정보들은 같은 인덱스를 가지게 됩니다.
        // 파라미터의 이름, 타입을 통해서 객체의 값을 찾습니다.
        for (int i = 0; i < parameterNames.length; i++) {
            System.out.println(parameterNames[i] + " " + parameterTypes[i].toString());
            // loginMemberId를 찾는 로직
            if (parameterNames[i].equals(MEMBER_PARAM) && parameterTypes[i].equals(Member.class) && member == null) {
                member = (Member)args[i];
            }
        }

        // 타입을 캐스팅해서 객체를 생성, 반환합니다.
        return member;
    }
}
