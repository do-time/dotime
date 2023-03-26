package com.jhd.dotime.common.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // runtime시 유지
@Target(ElementType.PARAMETER) // 파라미터에 사용할 수 있도록
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") // spEL을 이용해 인증정보가 존재하지 않으면 null, 존재한다면 member라는 property 반환
public @interface CurrentMember {

}
