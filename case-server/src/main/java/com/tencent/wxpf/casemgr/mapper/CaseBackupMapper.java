package com.tencent.wxpf.casemgr.mapper;

import com.tencent.wxpf.casemgr.entity.persistent.CaseBackup;
import com.tencent.wxpf.casemgr.service.impl.CaseBackupServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * 备份映射
 *
 * @author jeeffzheng
 * @date 2020/11/5
 * @see CaseBackup
 */
@Repository
public interface CaseBackupMapper {

    /**
     * 获取一份用例下所有的用例备份记录
     *
     * @param caseId 用例id
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 所有备份记录
     */
    List<CaseBackup> selectByCaseId(@Param("caseId") Long caseId,
                                    @Param("beginTime") Date beginTime,
                                    @Param("endTime")  Date endTime);

    /**
     * 删除一批备份记录
     *
     * @param caseId 用例id
     * @return int
     * @see CaseBackupServiceImpl#deleteBackup(java.lang.Long)
     */
    int updateByCaseId(Long caseId);

    /**
     * 插入备份记录
     *
     * @param caseBackup 实体
     * @return int
     */
    int insert(CaseBackup caseBackup);
}
