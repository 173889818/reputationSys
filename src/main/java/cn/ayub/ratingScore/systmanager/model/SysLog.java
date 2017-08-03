package cn.ayub.ratingScore.systmanager.model;

import cn.ayub.ratingScore.commons.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dingcc on 2017/7/6.
 */
@TableName("sys_log")
public class SysLog implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -120149365636645848L;
    /** 主键id */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 登陆名 */
    @TableField(value = "login_name")
    private String loginName;

    /** 角色名 */
    @TableField(value = "role_name")
    private String roleName;

    /** 内容 */
    @TableField(value = "opt_content")
    private String optContent;

    /** 客户端ip */
    @TableField(value = "client_ip")
    private String clientIp;

    /** 创建时间 */
    @TableField(value = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysLog:"+JsonUtils.toJson(this);
    }

}
