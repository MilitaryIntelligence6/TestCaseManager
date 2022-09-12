package com.tencent.wxpf.casemgr.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 任务的一个简单转换体
 *
 * @author jeeffzheng
 * @date 2020/10/29
 */
@Data
public class RecordWsDto {

    private Date updateTime;

    private String executors;

    private Integer env;

    private String caseContent;

    private String chooseContent;
}
