package com.jhd.dotime.members.common.jwt;

import static com.google.common.base.Preconditions.checkNotNull;

public class JwtAuthentication {

    public final String id;

    JwtAuthentication(String id) {
        checkNotNull(id, "id must be provided");

        this.id = id;
    }

}