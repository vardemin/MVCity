package com.vardemin.vcity.mvp.models.other;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xavie on 30.07.2017.
 */

public class LocalAuth {
    @SerializedName("strategy")
    private String strategy = "local";
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LocalAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}