package cn.ayub.ratingScore.systmanager.model.vo;

import cn.ayub.ratingScore.systmanager.model.SysUser;

import java.util.Set;

/**
 * Created by dingcc on 2017/7/25.
 */
public class SysUserVo extends SysUser {
    private static final long serialVersionUID = -7451459747483955961L;
    private Set<String> urls;//用户URL
    private Set<String> role;//用户角色
    private Set<String> organizationName;//用户组织

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Set<String> getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(Set<String> organizationName) {
        this.organizationName = organizationName;
    }



}
