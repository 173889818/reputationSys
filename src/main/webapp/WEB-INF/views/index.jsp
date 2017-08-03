<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
    <title>首页</title>
    <jsp:include page="/commons/basejs.jsp"></jsp:include>
</head>
<body>
<div>
    <center><font style="font-size: 18px;color: red;">首页-</font>首页</center>
   <%-- <p>首页</p>--%>
    <%--<textarea id="dd" onkeyup="getLength('dd')"></textarea>--%>
</div>

</body>
</html>

<script type="text/javascript" src="${ctx}/jslib/cookie.min.js"></script>
<script type="text/javascript">
    function max_char(s){
        var counts = {};
        var max_char = {char: '', count:50};
        var count=0;
        console.log("c:"+s.length);
        for(var i=0;i<s.length;i++){
            var c = s.charAt(i);
            if(!counts[c]){
                counts[c] = 1;
                count+=1;
            }else{
                console.log("e")
                counts[c] += 1;
                count+=2;
            }

            if(counts[c] > max_char['count']){
                max_char['char'] = c;
                max_char['count'] = counts[c];
            }
        }
        console.log("count:"+count);
        return max_char;
    }

    function getLength(strId) {
        var str=$('#'+strId).val();
       // var str=$(this).val();
        console.log(str);
        var count=getstrLength(str,10);
        console.log("num:"+count.len);
        if (count.len>10){
            $('#'+strId).val(count.maxlen);
           // $(this).val(count.maxlen);
        }
    }

    function getstrLength(str,charLen) {
        var count=new Object();
        var len = 0;
        var maxlen="";
        if (str!=undefined && str!=null && $.trim(str).length!=0) {
            for (var i = 0; i < str.length; i++) {
                if (str.charCodeAt(i) > 127 || str.charCodeAt(i) == 94) {
                    if(len<(charLen-1)){
                        maxlen += str.charAt(i);
                    }
                    len += 2;
                } else {
                    if(len<charLen){
                        maxlen += str.charAt(i);
                    }
                    len++;
                }
            }
        }
        count.len=len;
        count.maxlen=maxlen;
        return count;
    }
    $(document).ready(function() {
       /* var cookies = cookie();
        console.log(cookies);
        var token = cookie.get('token');
        console.log('token1:' + token);*/

      // console.log(getLength("xiao1 中国123"));
    });
   /* var cookies1 = cookie();
    console.log(cookies1);
    var tokenAll = cookie.all();
    console.log('tokenAll:' + tokenAll);*/
  /*  var token1 = cookies1.get('token');
    console.log('token1:' + token1);*/


</script>
