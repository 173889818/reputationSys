package cn.ayub.ratingScore.commons.result;

/**
 * Created by dingcc on 2017/7/5.
 */
public class ZTree implements java.io.Serializable{

    private static final long serialVersionUID = 1923898947761884341L;
    private String zId;     //节点id
    private String name;     //节点名称
    private Boolean checked=Boolean.FALSE;//是否选中
    private String iconCls;    //节点图标
    private String iconClose;  //节点关闭是图标
    private String iconOpen;   //节点打开时图标
    private Boolean isParent=Boolean.FALSE;//是否父节点默认否
    private String pId;//父节点ID
    private String state = "open";// open,closed open打开 closed 关闭

    public String getzId() {
        return zId;
    }

    public void setzId(String zId) {
        this.zId = zId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
