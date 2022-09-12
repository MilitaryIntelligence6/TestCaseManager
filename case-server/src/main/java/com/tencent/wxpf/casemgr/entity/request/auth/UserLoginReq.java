package com.tencent.wxpf.casemgr.entity.request.auth;

import lombok.Data;

/**
 * Created by jeeffzheng on 2021/4/22.
 */
@Data
public class UserLoginReq {
    private String username;

    private String password;
}
