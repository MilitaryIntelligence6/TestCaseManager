package com.tencent.wxpf.casemgr.entity.xmind;

import lombok.Data;

/**
 * 用例实际在websocket中传递的内容
 *
 * @author jeeffzheng
 * @date 2020/8/13
 */
@Data
public class CaseContent {

    private String template;

    private RootData root;

}


