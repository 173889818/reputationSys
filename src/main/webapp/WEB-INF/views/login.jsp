<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/commons/global.jsp"%>
<!doctype html>
<html>
<head>
    <title>hello</title>
   <%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />--%>
   <%-- <jsp:include page="/commons/global.jsp"/>--%>
    <jsp:include page="/commons/basejs.jsp"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css?v=20170726_1.1.10">
</head>
<body>
<form>
    <%--登录frame-div--%>
    <div id="login_frame">
        <%--左侧图片等内容--%>
        <div id="img_left">
            <img id="left_imges" src="">
        </div>
        <%--右侧登录内容--%>
            <div id="login_content">
                <p id="lable_login">系统登录</p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="login_auth login_lable"> 账号：</div>
                <div id="login_input_account"  class="login_auth">
                    <input type="text" id="user_account" name="userAccount" placeholder="请输入账号" class="login_info" value="">
                </div>
                <div class="login_auth login_lable"> 密码：</div>
                <div id="login_input_password" class="login_auth">
                    <input type="password" placeholder="请输入密码" name="userPassword" id="user_password" class="login_info" value="">
                </div>
                <div class="login_auth login_lable" style="position: relative;float: left;">
                    <%--position: relative;--%>
                    <input class="captcha" type="text" name="captcha" placeholder="请输入验证码" id="captcha_input"/>
                    <img id="captcha" alt="验证码" src="${ctx}/captcha.jpg" data-src="${ctx}/captcha.jpg?t=" style="vertical-align:middle;border-radius:4px;width:95px;height:30px;cursor:pointer;">
                    <p id="remberPassword">
                        <input class="rememberMe" type="checkbox" name="rememberMe" value="1" checked style="vertical-align:middle;"/> <label>记住密码</label>
                    </p>
                </div>
                <div id="submitBtn">
                    <input type="button" value="提&nbsp;&nbsp;&nbsp;&nbsp;交" id="login_submit">
                </div>
            </div>
    </div>

</form>
</body>
</html>
<script type="text/javascript" src="${ctx}/jslib/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/jslib/cookie.min.js"></script>
<script type="text/javascript">

    var authCode=false; //验证码默认为没校验为false
   // console.log("${ctx}/jslib/cookie.min.js");
    //设置cookie，并设置过期时间7天，路径、域
    $(document).ready(function(){
        //var cookies= cookie();
        //console.log(cookies);
        //var token=cookie.get('token');
        //console.log('token:'+token);
        setUrl();

     /*   $("#login_submit").bind("click",function(){
            if (!valiLoginData()){
                console.log($('.rememberMe').is(":selected"));
                if ($('.rememberMe').is(":selected")){
                    login();
                }else {
                    $('.rememberMe').focus();
                }

            }

        });*/
    });


    //登录
   function login(){
       var url='${ctx}/login';
       var loginData=$("form").serialize();
       $.post(url, loginData,function(data){
           if(data.success){
               window.location.href = '${ctx}'+'/index';
           }else{
               $("#captcha")[0].click();
               alert(data.msg);
           }
       }, "json");

   }
   //提交登录
   $('#login_submit').on('click', function(){
       console.log(authCode);
       if (!valiLoginData() && authCode){
           login();
       }
   });

   $(document).keyup(function(event){
       if(event.keyCode ==13) {//回车
           if (!valiLoginData() && authCode){
               login();
           }
       }
       if(event.keyCode ==9) {//tab
           if (!valiLoginData()){
           }
       }
   });


    $('.captcha').blur( function(){
        if (valiCaptcha('captcha_input',4)){//验证码长度
            if(!validateCaptcha()){//验证码不通过时
                authCode=true;
            }else{
                authCode=false;
            }

        }else{
            $('.captcha').val("");
            $('.captcha')[0].focus();
        }

    } )

   // 验证码
   $("#captcha").click(function() {
       var $this = $(this);
       var url = $this.data("src") + new Date().getTime();
       $this.attr("src", url);
   });

  /**
   *验证登录文本是否为空
   */
   function valiLoginData() {
       if(validateEmpty("user_account")){
           return true;
       }
       if(validateEmpty("user_password")){
           return true;
       }
       if(validateEmpty("captcha_input")){
           return true;
       }
       if (!valiCaptcha('captcha_input',4)){//验证不通过
           return true;
       }
        return false;
   }


   /**
    *如果不等于 lengths 返回false
    * @param captchaId 元素id
    * @param lengths 验证的长度
    */

    function valiCaptcha(captchaId,lengths){
       var captcha=$('#'+captchaId).val();
       if(captcha.length==lengths||captcha.length===lengths){//验证验证码长度
           return true;
       }
           return false;
    }
   /**
    * 验证是否为空
    * @param dataId 元素ID
    * @returns {boolean}
    */
   function  validateEmpty(dataId) {
       var record=$('#'+dataId).val();
       if(record==undefined||record==null||$.trim(record).length==0){
           $("#"+dataId).focus();
           //$("#"+dataId)[0].focus();
           return true;
       }
       return false;
   }

   /**
    *验证验证码是否准确
    */
   function validateCaptcha() {
       var urls='${ctx}/validateCaptcha';
       var captcha=$('#captcha_input').val();
       var flag=false;
       $.ajax({
           url: urls,
           processData: false,
           async:false,//默认为false
           type:"post",
           dataType:"json",
           data: "captcha="+captcha,
           success: function(data){
               console.log(data)
               if (data.success){
                   authCode=true;
                   return flag=true;
               }else{
                   $(".captcha").val("");
                   $("#captcha")[0].click();
                   return flag;
               }
           },statusCode: {
               404:function() {
                   alert('page not found');
               },302:function() {
                   alert('url change');
               },500:function() {
                   alert('system error');
               }
           }

       });

   }

    function getBase64Image(img) {

        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, img.width, img.height);
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();
        var dataURL = canvas.toDataURL("image/"+ext);
       // img.src = dataURL;
        return dataURL;
    }

    function setUrl(){
        var img = new Image();
        img.src = "${ctx}/static/style/images/20170725165512.png?"+Math.random();;
        var imgUrl=getBase64Image(img);
        img.onload=function () {
            $("#left_imges").attr("src",imgUrl);
        }
    }
</script>
