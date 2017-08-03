package cn.ayub.ratingScore.systmanager.service.impl;

import cn.ayub.ratingScore.commons.result.PageInfo;
import cn.ayub.ratingScore.commons.utils.BeanUtils;
import cn.ayub.ratingScore.commons.utils.StringUtils;
import cn.ayub.ratingScore.systmanager.common.sysUserStatus;
import cn.ayub.ratingScore.systmanager.mapper.SysUserMapper;
import cn.ayub.ratingScore.systmanager.model.SysUser;
import cn.ayub.ratingScore.systmanager.model.vo.SysUserVo;
import cn.ayub.ratingScore.systmanager.service.ISysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dingcc on 2017/7/25.
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService,Serializable {
    @Autowired
    private SysUserMapper userMapper;
    /*@Autowired
    private UserRoleMapper userRoleMapper;*/

    public List<SysUser> getSysUserByloginAccount(SysUser sysUser){
        //SysUser user= BeanUtils.copy(sysUser,SysUser.class);
        List<SysUser> list=userMapper.getAllUser(sysUser);
        if (CollectionUtils.isNotEmpty(list)){
            return list;
        }
        /*EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>(sysUser);
        if (StringUtils.isNotBlank(sysUser.getUserId())) {
            wrapper.where("userId != {0}", sysUser.getUserId());
        }*/
        return null;
        //return this.selectList(wrapper);
    }

    public void insetUser(SysUserVo sysUserVo){
        SysUser sysUser= BeanUtils.copy(sysUserVo,SysUser.class);
        sysUser.setCreateTime(new Date());
        this.insert(sysUser);
    }

    public void updateUser(SysUserVo sysUserVo){
        SysUser sysUser= BeanUtils.copy(sysUserVo,SysUser.class);
        if (StringUtils.isBlank(sysUser.getPassword())) {
            sysUser.setPassword(null);
        }
        this.updateById(sysUser);
    }
    public void UpdateUserPwdbyId(String userId,String md5Hex){
        SysUser sysUser=new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(md5Hex);
        this.updateById(sysUser);
    }

    public void deleteUserById(String userId){
        SysUser sysUser=new SysUser();
        sysUser.setUserId(userId);
        sysUser.setStatus(sysUserStatus.STATUS_DELETE);
        this.updateById(sysUser);
    }

    public void selectPageData(PageInfo pageInfo){
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
        page.setOrderByField(pageInfo.getSort());
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
        List<Map<String, Object>> list = userMapper.selectUserPage(page, pageInfo.getCondition());

    }
}
