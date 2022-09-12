package com.tencent.wxpf.casemgr.entity.response.dir;

import lombok.Data;

/**
 * 返回给前端的所有文件夹的列表
 *
 * @author jeeffzheng
 * @date 2020/9/16
 */
@Data
public class BizListResp {

    private String bizId;

    private String text;

    private boolean select;
}
