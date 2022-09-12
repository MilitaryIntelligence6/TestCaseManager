package com.tencent.wxpf.casemgr.controller;

import com.tencent.wxpf.casemgr.constants.enums.StatusCode;
import com.tencent.wxpf.casemgr.entity.exception.CaseServerException;
import com.tencent.wxpf.casemgr.entity.persistent.CaseBackup;
import com.tencent.wxpf.casemgr.entity.response.controller.Response;
import com.tencent.wxpf.casemgr.service.CaseBackupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 备份controller
 *
 * @author jeeffzheng
 * @date 2020/11/5
 */
@RestController
@RequestMapping(value = "/api/backup")
public class BackupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackupController.class);

    @Resource
    CaseBackupService caseBackupService;

    /**
     * 查询某个用例所有的备份记录
     *
     * @param caseId 用例id
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 响应体
     */
    @GetMapping(value = "/getBackupByCaseId")
    public Response<List<CaseBackup>> getBackupByCaseId(@RequestParam @NotNull(message = "用例id为空") Long caseId,
                                                        @RequestParam(required = false) String beginTime,
                                                        @RequestParam(required = false) String endTime) {
        return Response.success(caseBackupService.getBackupByCaseId(caseId, beginTime, endTime));
    }

    /**
     * 删除某个用例所有的备份记录
     *
     * @param caseId 实体，本市上这里应该包装一层Request
     * @return 响应体
     */
    @GetMapping(value = "/deleteByCaseId")
    public Response<Integer> deleteByCaseId(@RequestParam Long caseId) {
        return Response.success(caseBackupService.deleteBackup(caseId));
    }

    /**
     * 创建备份
     *
     * @param caseBackup 实体，本市上这里应该包装一层Request
     * @return 响应体
     */
    @PostMapping(value = "/add")
    public Response<CaseBackup> addBackup(@RequestBody CaseBackup caseBackup) {
        try {
            return Response.success(caseBackupService.insertBackup(caseBackup));
        } catch (CaseServerException e) {
            throw new CaseServerException(e.getLocalizedMessage(), e.getStatus());
        } catch (Exception e) {
            LOGGER.error("[Dir add]Add dir failed. params={} e={} ", caseBackup.toString(), e.getMessage());
            e.printStackTrace();
            return Response.build(StatusCode.SERVER_BUSY_ERROR);
        }

    }
}
