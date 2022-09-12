package com.tencent.wxpf.casemgr.entity.request.ws;

import com.tencent.wxpf.casemgr.entity.request.ParamValidate;import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 任务 脑图当中清理执行记录的按钮
 *
 * @author jeeffzheng
 * @date 2020/10/29
 */
@Data
public class RecordWsClearReq implements ParamValidate {

    private Long id;

    private String modifier;

    @Override
    public void validate() {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("任务id为空或非法");
        }
        if (StringUtils.isEmpty(modifier)) {
            throw new IllegalArgumentException("修改人为空");
        }
    }
}
