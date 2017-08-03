<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<link rel="shortcut icon" href="${ctx}/static/style/images/favicon.ico" />--%>
<%-- [my97日期时间控件] --%>
<%--
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
--%>
<%-- [jQuery] --%>
<script type="text/javascript" src="${ctx}/jslib/jquery-3.2.1.min.js?v=20170726.3.2.1"></script>
<%--bootstrap css--%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap-theme.min.css">
<%--bootstrap.js--%>
<script type="text/javascript" src="${ctx}/jslib/bootstrap/bootstrap.min.js?v=20170728.3.3.7"></script>
<%--des encrypt--%>
<%--
<script type="text/javascript" src="${ctx}/jslib/encryption/CryptoJS%20v3.1.2/components/core.js?v=20170726CryptoJS.3.1.2"></script>
--%>
<script type="text/javascript" src="${ctx}/jslib/encryption/CryptoJS%20v3.1.2/rollups/tripledes.js?v=20170726CryptoJS.3.1.2"></script>
<script type="text/javascript" src="${ctx}/jslib/encryption/CryptoJS%20v3.1.2/components/mode-ecb.js?v=20170726CryptoJS.3.1.2"></script>
<script type="text/javascript" src="${ctx}/jslib/encryption/hermitDes.js?v=20170726CryptoJS.3.1.2"></script>
<%-- [EasyUI] --%>
<%--<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/gray/easyui.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/icon.css" />
<script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>--%>
<%-- [扩展JS] --%>
<%--
<script type="text/javascript" src="${ctx}/static/extJs.js" charset="utf-8"></script>
--%>
<%-- [扩展样式] --%>
<%--<link rel="stylesheet" type="text/css" href="${ctx}/static/style/css/dreamlu.css?v=10" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/foundation-icons/foundation-icons.css" />--%>
<script type="text/javascript">
    var basePath = "${ctx}";
    window.UEDITOR_HOME_URL = "${ctx}/static/ueditor/";
</script>