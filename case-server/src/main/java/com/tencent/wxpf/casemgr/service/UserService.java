package com.tencent.wxpf.casemgr.service;

import com.tencent.wxpf.casemgr.entity.request.auth.UserLoginReq;
import com.tencent.wxpf.casemgr.entity.request.auth.UserRegisterReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jeeffzheng on 2021/4/22.
 */
public interface UserService {
    Integer register(UserRegisterReq req, HttpServletRequest request, HttpServletResponse response);
    Integer login(UserLoginReq req, HttpServletRequest request, HttpServletResponse response);
    Integer logout(HttpServletRequest request, HttpServletResponse response);
}
