package com.jhd.dotime.members.common.jwt;

import com.jhd.dotime.members.common.exception.MemberNotFoundException;
import com.jhd.dotime.members.dto.Email;
import com.jhd.dotime.members.dto.Role;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.service.MemberService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;

    public JwtAuthenticationProvider(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processUserAuthentication(
                Email.of(String.valueOf(authenticationToken.getPrincipal())),
                authenticationToken.getCredentials()
        );
    }

    private Authentication processUserAuthentication(Email email, String password) {
        try {
            Member member = memberService.login(email, password);
            JwtAuthenticationToken authenticated =
                    new JwtAuthenticationToken(
                            new JwtAuthentication(member.getEmail()),
                            null,
                            createAuthorityList(Role.USER.value())
                    );
            authenticated.setDetails(member);
            return authenticated;
        } catch (MemberNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isAssignable(JwtAuthenticationToken.class, authentication);
    }

}