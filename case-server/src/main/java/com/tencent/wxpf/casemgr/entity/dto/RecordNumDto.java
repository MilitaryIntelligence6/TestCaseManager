package com.tencent.wxpf.casemgr.entity.dto;

import lombok.Data;


/**
 * 执行任务数量转换体
 *
 * @author jeeffzheng
 * @date 2020/6/9
 */
@Data
public class RecordNumDto {

    /**
     * 所属用例id
     */
    Long caseId;

    /**
     * 任务数量
     */
    Integer recordNum;

}
