package com.tencent.wxpf.casemgr.entity.dto;

import com.tencent.wxpf.casemgr.service.impl.RecordServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 给getData专用的转换体
 *
 * @author jeeffzheng
 * @date 2020/10/28
 * @see RecordServiceImpl#getData(MergeCaseDto)
 */
@Data
@AllArgsConstructor
public class MergeCaseDto {

    private Long caseId;

    private String chooseContent;

    private String recordContent;

    private Integer env;

    private Long recordId;
}
