package com.tencent.wxpf.casemgr.entity.request.cases;

import com.tencent.wxpf.casemgr.entity.request.ParamValidate;
import lombok.Data;

/**
 * 用例 逻辑删除
 *
 * @author jeeffzheng
 * @date 2020/9/7
 */
@Data
public class CaseDeleteReq implements ParamValidate {

    private Long id;

    @Override
    public void validate() {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("用例id为空");
        }
    }
}
