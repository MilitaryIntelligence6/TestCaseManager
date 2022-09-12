package com.tencent.wxpf.casemgr.entity.request.record;

import com.tencent.wxpf.casemgr.entity.request.ParamValidate;
import lombok.Data;

/**
 * 任务 删除
 *
 * @author jeeffzheng
 * @date 2020/10/28
 */
@Data
public class RecordDeleteReq implements ParamValidate {

    private Long id;

    @Override
    public void validate() {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("任务id为空或不正确");
        }
    }
}
