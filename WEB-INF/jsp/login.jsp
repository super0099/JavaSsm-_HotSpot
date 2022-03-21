<%--
  Created by IntelliJ IDEA.
  User: super007
  Date: 2021/11/23
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>
<html>
<head>
    <title>hotSpotLogin</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        #text{
            width: 20%;
            height: 20%;
            background: #01AAED;
        }
    </style>
</head>
<body>
    <div id="login-box">
        <img src="${ctx}/static/img/201用户_用户2.png" class="userPortrait" id="userPortrait">
        <div class="form">
            <div class="userName" style="margin-bottom: 10px">
                <img src="${ctx}/static/img/账号.png">
                <input type="text" autocomplete="off" placeholder="账号" id="userName" onblur="onblurs()">
            </div>
            <div class="password">
                <img src="${ctx}/static/img/密码%20(1).png">
                <input type="password" placeholder="密码" id="password">
            </div>
        </div>
        <button type="button" onclick="login()">Login</button>
        <div class="registerAndForget clearfix">
            <span onclick="registers()">注册</span>
            <span></span>
        </div>
    </div>
    <div class="registers" style="display: none;" id="registers">
        <h1>注册</h1>
        <div class="password">
            <img src="${ctx}/static/img/密码%20(1).png">
            <input type="password" placeholder="密码" id="onePassword">
        </div>
        <div class="password">
            <img src="${ctx}/static/img/密码%20(1).png">
            <input type="password" placeholder="确认密码" id="twoPassword">
        </div>
        <div class="password">
            <img src="${ctx}/static/img/手机号.png">
            <input type="password" placeholder="手机号" id="phone">
        </div>
        <div class="password">
            <img src="${ctx}/static/img/身份认证-身份证.png">
            <input type="password" placeholder="身份证号" id="idNumber">
        </div>
        <button type="button" onclick="registered()">register</button>
        <div class="registerAndForget clearfix">
            <span onclick="logined()">登录</span>
            <span></span>
        </div>
    </div>
<script src="${ctx}/static/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/static/js/lib/layui/layui.js" charset="UTF-8"></script>
<script>
    /*登录功能*/
    function login(){
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(userName==null||userName==undefined||userName==""){
            layer.msg("请输入账号");
            return;
        }
        if(password==null||password==undefined||password==""){
            layer.msg("请输入密码");
            return;
        }
        $.post("${ctx}/login/login",{
            userName:userName,
            password:password
        },function (jsonData){
            if (jsonData.state){
                window.location.replace("${ctx}/home/index");
            }else {
                layer.msg(jsonData.msg)
            }
        })
    }
    /*注册功能*/
    function registered(){
        var onePassword = $("#onePassword").val();
        var twoPassword = $("#twoPassword").val();
        var phone = $("#phone").val();
        var idNumber = $("#idNumber").val();
        var idCar = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
        var phoneText = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
        if (onePassword==undefined||onePassword==null||onePassword==""){
            layer.msg("密码不能为空");
            return;
        }
        if (twoPassword==undefined||twoPassword==null||twoPassword==""){
            layer.msg("确认密码不能为空");
            return;
        }
        if (!phoneText.test(phone)){
            layer.msg("手机号不能为空或者格式错误");
            return;
        }
        if (!idCar.test(idNumber)){
            layer.msg("身份证号不能为空或者格式错误");
            return;
        }
        $.post("${ctx}/login/register",{
            onePassword:onePassword,
            twoPassword:twoPassword,
            phone:phone,
            idNumber:idNumber
        },function (jsonData){
            console.log(jsonData)
            if (jsonData.state){
                var login = document.getElementById("login-box");
                var registers = document.getElementById("registers");
                login.style.display="block";
                registers.style.display="none";
                $("#onePassword").val("")
                $("#twoPassword").val("")
                $("#phone").val("");
                $("#idNumber").val("");
                $("#password").val("");
                document.getElementById("userPortrait").setAttribute("src","${ctx}/login/getPortraitImage?imgName="+jsonData.data.portrait);
                $("#userName").val(jsonData.data.userName);

            }else {
                layer.msg(jsonData.msg)
            }
        })
    }
    /*输入账号显示头像*/
    function onblurs(){
        var userName = $("#userName").val();
        $.post("${ctx}/login/onblur",{
            userName:userName,
        },function (jsonData){
            if(jsonData.state){
                document.getElementById("userPortrait").setAttribute("src","${ctx}/login/getPortraitImage?imgName="+jsonData.data.portrait);
            }
        })
    }
    /*点击注册跳转*/
    function registers(){
        var login = document.getElementById("login-box");
        var registers = document.getElementById("registers");
        login.style.display="none";
        registers.style.display="block";
    }
    /*点击登录跳转*/
    function logined(){
        var login = document.getElementById("login-box");
        var registers = document.getElementById("registers");
        login.style.display="block";
        registers.style.display="none";
    }
    function newPage(o){
        alert(o)
    }
</script>
</body>
</html>
