package com.tencent.wxpf.casemgr.service;

import com.tencent.wxpf.casemgr.entity.persistent.ExecRecord;
import com.tencent.wxpf.casemgr.entity.request.record.RecordAddReq;
import com.tencent.wxpf.casemgr.entity.request.record.RecordUpdateReq;
import com.tencent.wxpf.casemgr.entity.dto.RecordWsDto;
import com.tencent.wxpf.casemgr.entity.request.ws.RecordWsClearReq;
import com.tencent.wxpf.casemgr.entity.response.records.RecordGeneralInfoResp;
import com.tencent.wxpf.casemgr.entity.response.records.RecordListResp;

import java.util.List;

/**
 * 执行任务接口
 *
 * @author jeeffzheng
 * @date 2020/8/18
 */
public interface RecordService {

    /**
     * 根据用例集caseId，查询该用例集下所有的执行任务
     *
     * @param caseId 任务所属的用例集id
     * @return 执行任务列表
     */
    List<RecordListResp> getListByCaseId(Long caseId);

    /**
     * 协同页面，获取上方基础信息
     *
     * @param recordId 操作记录id
     * @return 基础信息
     */
    RecordGeneralInfoResp getGeneralInfo(Long recordId);

    /**
     * 添加执行任务
     *
     * @param req 请求体
     * @return 任务id
     */
    Long addRecord(RecordAddReq req);

    /**
     * 逻辑删除执行任务
     *
     * @param recordId 任务id
     */
    void delete(Long recordId);

    /**
     * 编辑执行任务的属性
     *
     * @param req 请求体
     */
    void editRecord(RecordUpdateReq req);

    /**
     * 给websocket使用的获取执行任务的方法
     *
     * @param recordId 任务id
     * @return 转换体
     */
    RecordWsDto getWsRecord(Long recordId);

    /**
     * 修改记录
     *
     * @param record 任务实体
     */
    void modifyRecord(ExecRecord record);

    /**
     * 协同页面，清除执行记录
     *
     * @param req 请求体
     * @return 由于不知道前端到底用了什么字段，所以就直接返回entity吧
     */
    ExecRecord wsClearRecord(RecordWsClearReq req);

}
