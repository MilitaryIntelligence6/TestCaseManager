package com.tencent.wxpf.casemgr.entity.response.cases;

import lombok.Data;

/**
 * 脑图-查看用例上方的概览信息(不包括content)
 *
 * @author jeeffzheng
 * @date 2020/10/22
 */
@Data
public class CaseGeneralInfoResp {

    private Long id;

    private String title;

    private String requirementId;

    private Long productLineId;
}
