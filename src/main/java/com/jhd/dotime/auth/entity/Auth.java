package com.jhd.dotime.auth.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "auth")
@Getter
@NoArgsConstructor
public class Auth {
    @Id
    @Column(name = "authority_name")
    private String authorityName;

    @Builder
    public Auth(String authorityName){
        this.authorityName = authorityName;
    }
}
