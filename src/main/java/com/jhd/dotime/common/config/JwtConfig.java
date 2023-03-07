package com.jhd.dotime.common.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Getter
@Component
public class TokenProvider {
    protected final Logger logger = LoggerFactory.getLogger(com.jhd.dotime.common.config.TokenProvider.class);

    protected static final String AUTHORITIES_KEY = "auth";

    protected final String secret;
    protected final long tokenValidityInMilliseconds;
    protected Key key;

    public TokenProvider(String secret, long tokenValidityInMilliseconds){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds*1000;
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }


}