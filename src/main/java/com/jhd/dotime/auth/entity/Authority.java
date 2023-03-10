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
public class Authority {
    @Id
    @Column
    private String authorityName;

    @Builder
    public Authority(String authorityName){
        this.authorityName = authorityName;
    }
}
