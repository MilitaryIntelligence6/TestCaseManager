package com.tencent.wxpf.casemgr.entity.request.dir;

import com.tencent.wxpf.casemgr.constants.BizConstant;
import com.tencent.wxpf.casemgr.entity.request.ParamValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 文件夹 删除
 *
 * @author jeeffzheng
 * @date 2020/9/11
 */
@Data
@AllArgsConstructor
public class DirDeleteReq implements ParamValidate {

    private String parentId;

    private Long productLineId;

    private String delId;

    private Integer channel;

    @Override
    public void validate() {
        if (productLineId == null || productLineId <= 0) {
            throw new IllegalArgumentException("业务线id为空或者非法");
        }
        if (StringUtils.isEmpty(delId)) {
            throw new IllegalArgumentException("要删除的文件夹id不能为空");
        }
        if (BizConstant.ROOT_BIZ_ID.equals(delId)) {
            throw new IllegalArgumentException("不能删除根文件夹");
        }
        if (StringUtils.isEmpty(parentId)) {
            throw new IllegalArgumentException("父文件夹id为空");
        }
        if (channel == null) {
            throw new IllegalArgumentException("渠道为空");
        }
    }
}
