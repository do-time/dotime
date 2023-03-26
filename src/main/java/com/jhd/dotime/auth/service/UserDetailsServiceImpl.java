package com.jhd.dotime.auth.service;

import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.members.vo.MemberAdapter;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;

    }


    /*
     * authenticationManangerBuilder.getObject().authenticate() 메소드가 실행되면
     *   1. AuthenticationManager 의 구현체인 ProviderManager 의 authenticate() 메소드가 실행됨
     *   2. 해당 메소드에선 AuthenticaionProvider 인터페이스의 authenticate() 메소드를 실행하는데
     *      해당 인터페이스에서 데이터베이스에 있는 이용자의 정보를 가져오는  UserDetailsService 인터페이스를 사용
     *   3. 그래서 UserDetailsService 인터페이스의 loadUserByUsername() 메소드를 호출
     * 따라서 CustomUserDetailsService 구현체에 오버라이드된 loadUserByUsername() 메소드를 호출
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new MemberAdapter(member);
    }
}
