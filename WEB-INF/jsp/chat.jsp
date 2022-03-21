<%--
  Created by IntelliJ IDEA.
  User: super007
  Date: 2021/12/14
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="${ctx}/static/css/chat.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div class="chat_content">
        <%--主键--%>
        <input type="text" hidden value="${user.majorKeyId}" id="majorKeyId">
        <%--我的Id--%>
        <input type="text" hidden value="${myUser.id}" id="myUserId">
        <%--聊天状态--%>
        <input type="text" hidden value="${user.state}" id="stateCode">
        <%--文件--%>
        <input type="file" hidden id="pictureAndVideo" onchange="alter()">
        <%--头部--%>
        <div class="chat_content_head">
            <p>${user.name}</p>
            <img src="${ctx}/static/img/更多功能-my.png">
        </div>
        <%--信息显示区--%>
        <div class="chat_content_body">
            <div class="messageFriend clearfix">
                <p>杨雄茗</p>
                <%--头像--%>
                <div class="friendDataPortrait">
                    <%--头像框--%>
                    <img src="${ctx}/static/img/冬天.png">
                    <%--头像--%>
                    <div class="content_felt_material_head_portrait">
                        <img src="${ctx}/static/img/Imgpage.jpg">
                    </div>
                </div>
                <%--信息--%>
                <div class="sendDataMessage">你好那还你好哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或 </div>
            </div>
            <div class="messageMy clearfix">
                <p>杨雄茗</p>
                <%--头像--%>
                <div class="myDataPortrait">
                    <%--头像框--%>
                    <img src="${ctx}/static/img/冬天.png">
                    <%--头像--%>
                    <div class="my_content_felt_material_head_portrait">
                        <img src="${ctx}/static/img/Imgpage.jpg">
                    </div>
                </div>
                <%--信息--%>
                <div class="my_sendDataMessage">你好那还你好哈</div>
            </div>
        </div>
        <%--功能区--%>
        <div class="chat_content_foot">
            <div class="operation clearfix">
                <img src="${ctx}/static/img/表情.png" onclick="openExpression(this)" id="openExpression">
                <img src="${ctx}/static/img/图片.png" ondblclick="openFileChoice()">
                <img src="${ctx}/static/img/视频通话.png">
            </div>
            <div class="dataTextSend" contenteditable="true" id="dataTextSend" onclick="closeExpression()"></div>
            <button class="sendData" onclick="sendMessage()">发送</button>
        </div>
        <%--表情包存放区--%>
        <div class="expression" id="expression" style="display: none">

        </div>
        <%--显示图片--%>
        <div class="imgAndVidShow" id="imgAndVidShow" style="display: none">
            <p>点我可拖动!<i onclick="cancelSend()">取消发送</i></p>
            <img src="" id="imgShow">
        </div>
    </div>
<script src="${ctx}/static/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/static/js/lib/layui/layui.js" charset="UTF-8"></script>
<script src="${ctx}/static/js/chatStyle.js" charset="UTF-8"></script>
<script>
    var ws;
    var childPage = true;
    var majorKeyIds = document.getElementById("majorKeyId").value;
    var myUserId = document.getElementById("myUserId").value;
    if(WebSocket){
        ws = new WebSocket("wss://192.168.191.1:8443/hotSpot/homeWebSocket");
    }else {
        alert("你的浏览器不支持webSocket");
    }
    ws.onmessage=function (event){
        var obj = JSON.parse(event.data);
        console.log(obj)
        if (obj.state ==4){
            if (childPage){
                /*判断信息是否发送成功*/
                if (obj.noProblem){
                    /*判断发送人是否等于当前用户*/
                    if (myUserId==obj.sendId){
                        //执行添加一条聊天记录,在右边

                    }else {
                        //执行添加一条聊天记录,在左边
                    }
                }else {
                    if (myUserId==obj.sendId){
                        layer.msg("发送失败")
                    }
                }
            }
        }

    }
    var j = 0;
    $(function (){
        var expression = document.getElementById("expression");
        for (var i =1;i<75;i++){
            expression.innerHTML+='<img src="${ctx}/static/img/face/'+i+'.gif" onclick="addText('+i+')">';
        }
        var drag = document.getElementById('imgAndVidShow');
        // //点击某物体时，用drag对象即可，move和up是全局区域，
        // 也就是整个文档通用，应该使用document对象而不是drag对象(否则，采用drag对象时物体只能往右方或下方移动)
        drag.onmousedown = function(event){
            var event = event || window.event;
            var diffX = event.clientX - drag.offsetLeft;
            var diffY = event.clientY - drag.offsetTop;
            if(typeof drag.setCapture !== 'undefined'){
                drag.setCapture();
            }
            document.onmousemove = function(event){
            var event = event || window.event;
            var moveX = event.clientX - diffX;
            var moveY = event.clientY - diffY;
            if(moveX < 0){
                moveX = 0
            }else if(moveX > window.innerWidth - drag.offsetWidth){
                moveX = window.innerWidth - drag.offsetWidth
            }
            if(moveY < 0){
                moveY = 0
            }else if(moveY > window.innerHeight - drag.offsetHeight){
                moveY =  window.innerHeight - drag.offsetHeight
            }
            drag.style.left = moveX + 'px';
            drag.style.top = moveY + 'px'
        }
            document.onmouseup = function(event){
            this.onmousemove = null;
            this.onmouseup = null;
            //修复低版本ie bug
            if(typeof drag.releaseCapture!='undefined'){
                drag.releaseCapture();
            }
        }
        }
    })
    /*打开表情包*/
    function openExpression(obj){
        var expression = document.getElementById("expression");
        if (j%2==0){
            obj.setAttribute("src","${ctx}/static/img/表情s.png");
            expression.style.display="block";
            j++;
        }else {
            obj.setAttribute("src","${ctx}/static/img/表情.png");
            expression.style.display="none";
            j++;
        }
    }
    /*发送信息*/
    function sendMessage(){
        var dataTextSend = document.getElementById("dataTextSend");
        var majorKeyId = document.getElementById("majorKeyId").value;
        var stateCode = document.getElementById("stateCode").value;
        document.getElementById("expression").style.display="none";
        document.getElementById("openExpression").setAttribute("src","${ctx}/static/img/表情.png");
        j=0;
        var pictureAndVideo = $("#pictureAndVideo").get(0).files[0];
        var sendStr = dataTextSend.innerHTML;
        if (pictureAndVideo!=undefined||pictureAndVideo!=null){
            var formData = new FormData();
            formData.append("fileText",pictureAndVideo);
            $.ajax({
                type: "POST",//文件上传 只能是post
                url: "${ctx}/chat/sendFile",
                data: formData,
                cache:false,
                processData:false,//禁止jquery对上传的数据进行处理
                contentType: false,
                dataType:'json',
                success: function(jsonMsg){
                    if (jsonMsg.state){
                        var sendObj = {
                            majorKeyId:majorKeyId,
                            stateCode:stateCode,
                            chatWay:2,
                            chatContent:jsonMsg.msg,
                            state:4,
                            sendId:myUserId
                        }
                        ws.send(JSON.stringify(sendObj));
                    }else{
                        layer.msg(jsonMsg.msg,{icon:5});
                    };
                }
            });
            /*清空图片*/
            var pictureAndVideo = document.getElementById("pictureAndVideo");
            var imgAndVidShow = document.getElementById("imgAndVidShow");
            imgAndVidShow.style.display="none";
            pictureAndVideo.value='';
            return;
        }
        var sendText = sendStr.replace(/<img src="\/[\w]*\/[\w]*\/[\w]*\/[\w]*\/([0-9]*).[\w]{3}">/g, '[em_$1]');
        var sendObj = {
            majorKeyId:majorKeyId,
            stateCode:stateCode,
            chatWay:1,
            chatContent:sendText,
            state:4,
            sendId:myUserId
        }
        ws.send(JSON.stringify(sendObj));
        dataTextSend.innerHTML="";
    }
    /*关闭表情*/
    function closeExpression(){
        document.getElementById("expression").style.display="none";
        document.getElementById("openExpression").setAttribute("src","${ctx}/static/img/表情.png");
        j=0;
    }
    /*选择图片*/
    function openFileChoice(){
        /*关闭表情包*/
        document.getElementById("expression").style.display="none";
        document.getElementById("openExpression").setAttribute("src","${ctx}/static/img/表情.png");
        j=0;
        var file = document.getElementById("pictureAndVideo");
        file.click();
    }
    /*取消图片选择*/
    function cancelSend(){
        var pictureAndVideo = document.getElementById("pictureAndVideo");
        var imgAndVidShow = document.getElementById("imgAndVidShow");
        imgAndVidShow.style.display="none";
        pictureAndVideo.value='';
    }
</script>
</body>
</html>
