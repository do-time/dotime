package com.jhd.dotime.auth;

import static com.google.common.base.Preconditions.checkNotNull;

public class JwtAuthentication {

    public final String id;

    JwtAuthentication(String id) {
        checkNotNull(id, "id must be provided");

        this.id = id;
    }

}