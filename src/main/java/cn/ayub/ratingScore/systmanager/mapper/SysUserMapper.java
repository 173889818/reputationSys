package cn.ayub.ratingScore.systmanager.mapper;

import cn.ayub.ratingScore.systmanager.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by dingcc on 2017/7/6.
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /*public SysUser getUser(String userId);*/

    public List<SysUser> getAllUser(SysUser user);

    public String insertUser(SysUser user);

    /*public void deleteUser(SysUser user);*/

    public void updateUser(SysUser user);

    List<Map<String, Object>> selectUserPage(Pagination page, Map<String, Object> params);

}
