package cn.ayub.ratingScore.systmanager.model;

import cn.ayub.ratingScore.commons.utils.JsonUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dingcc on 2017/7/6.
 */
@TableName("sys_user")
//@Alias("SysUser")
public class SysUser implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 8620621370514969193L;
    /**
     * 用户ID
     */
    @TableId(type = IdType.UUID)
    @TableField(value ="user_id")
    private String userId;//用户ID
    /**
     * 用户登录账户
     */
    @TableField(value ="login_account")
    private String loginAccount;
    /**
     * 用户名称
     */
    @TableField(value ="user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salf;

    @TableField(value ="id_card")
    private String idCard;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex; //0 男 1  女 2 人妖
    /**
     * 电话
     */
    private String telephone;
    /**
     * 地址
     */
    private String  address;
    /**
     * 用户类型
     */
    @TableField(value ="user_type")
    private Integer  userType;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 创建时间
     */

    @TableField(value ="create_time")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @TableField(value ="last_login_time")
    private String  lastLoginTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalf() {
        return salf;
    }

    public void setSalf(String salf) {
        this.salf = salf;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "SysUser:"+ JsonUtils.toJson(this);
    }
}
