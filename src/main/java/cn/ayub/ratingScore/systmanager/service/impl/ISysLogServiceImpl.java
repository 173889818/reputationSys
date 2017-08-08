package cn.ayub.ratingScore.systmanager.service.impl;

import cn.ayub.ratingScore.systmanager.mapper.SysLogMapper;
import cn.ayub.ratingScore.systmanager.model.SysLog;
import cn.ayub.ratingScore.systmanager.service.ISysLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.Serializable;

/**
 * Created by dingcc on 2017/8/1.
 */
 @Service
public class ISysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService,Serializable {
}
