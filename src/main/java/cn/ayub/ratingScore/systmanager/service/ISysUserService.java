package cn.ayub.ratingScore.systmanager.service;

import cn.ayub.ratingScore.commons.result.PageInfo;
import cn.ayub.ratingScore.systmanager.model.SysUser;
import cn.ayub.ratingScore.systmanager.model.vo.SysUserVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * Created by dingcc on 2017/7/25.
 */
public interface ISysUserService extends IService<SysUser> {
    List<SysUser> getSysUserByloginAccount(SysUser sysUser);

    void updateUser(SysUserVo sysUserVo);

    void UpdateUserPwdbyId(String userId,String md5Hex);

    void deleteUserById(String userId);

    void selectPageData(PageInfo pageInfo);
}
