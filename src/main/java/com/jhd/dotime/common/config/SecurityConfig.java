package com.jhd.dotime.common.config;

import com.jhd.dotime.auth.JwtAccessDeniedHandler;
import com.jhd.dotime.common.security.JwtAuthenticationEntryPoint;
import com.jhd.dotime.common.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler){
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    private static final String[] SWAGGER_URL = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
     * 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers(
                        "/h2/**"
                        ,"/favicon.ico"
                        ,"/error"
                );

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class) //UsernamePasswordAuthenticationFilter 전에 customFilter가 걸리게 한다.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패 핸들링
                .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 실패 핸들링

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .authorizeRequests()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/", "/**").permitAll() // 전체 경로 허가
                .antMatchers("/auth/**").permitAll()
                .antMatchers(SWAGGER_URL).permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)) // JwtSecurityConfig 적용
        ;



    }
}
