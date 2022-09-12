package com.tencent.wxpf.casemgr.entity.response.dir;

import com.tencent.wxpf.casemgr.entity.dto.DirNodeDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构
 *
 * @author jeeffzheng
 * @date 2020/11/11
 */
@Data
public class DirTreeResp {

    private List<DirNodeDto> children = new ArrayList<>();
}
