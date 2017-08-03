package cn.ayub.ratingScore.systmanager.mapper;


import cn.ayub.ratingScore.systmanager.model.SysLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dingcc on 2017/7/6.
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    public SysLog getSysLog(@Param("id") long id);

    public List<SysLog> getAllSysLog();

    public void insertSysLog(SysLog sysLog);

   /* public void deleteSysLog(SysLog sysLog);*/

    public void updateSysLog(SysLog sysLog);
}
