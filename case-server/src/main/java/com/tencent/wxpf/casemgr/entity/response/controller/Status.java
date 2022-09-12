package com.tencent.wxpf.casemgr.entity.response.controller;

/**
 * controller层统一响应体抽象类
 *
 * @author jeeffzheng
 * @date 2020/12/15
 */
public interface Status {
    /**
     * 是否成功状态
     *
     * @return 成功状态，返回true；否则，返回false
     */
    boolean isSuccess();

    /**
     * 状态码值
     *
     * @return 状态码值
     */
    int getStatus();

    /**
     * 错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 状态描述
     *
     * @return 状态描述
     */
    String getMsg();

    /**
     * 状态描述
     *
     * @return 状态描述
     */
    String getMsg(Object... format);
}
