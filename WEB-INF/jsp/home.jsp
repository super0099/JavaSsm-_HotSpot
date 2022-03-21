<%--
  Created by IntelliJ IDEA.
  User: super007
  Date: 2021/11/24
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>
<html>
<head>
    <title>hotSpotHome</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,initial-scale=1.0,width=device-width" />

    <link rel="stylesheet" href="${ctx}/static/css/home.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        if (window.top.location.href !== window.location.href) {
            window.top.location.href = window.location.href;
        }
    </script>
</head>
<body style="background: url('${ctx}/login/getPortraitImage?imgName=${sysIndividuality.getBackground()}');background-size: 100% 100%;">
    <%--功能区--%>
    <div class="hotSpotHome_content">
        <%--联系人显示区--%>
        <div class="content_felt">
            <%--头像功能--%>
            <div class="content_felt_material">
                <%--头像--%>
                <div class="content_felt_material_head">
                    <%--头像框--%>
                    <img src="${ctx}/login/getPortraitImage?imgName=${sysIndividuality.getPortraitFrame()}">
                    <%--头像--%>
                    <div class="content_felt_material_head_portrait">
                        <img src="${ctx}/login/getPortraitImage?imgName=${sysIndividuality.getPortrait()}" alt="">
                    </div>
                </div>
                <div class="content_felt_material_nickname">
                    ${sysUserVo.getNickName()}
                </div>
                <%--功能--%>
                <div class="content_felt_material_function">
                    <i class="content_felt_material_function_add" onclick="addGroup()"></i>
                    <i class="content_felt_material_functions" onclick="personality()"></i>
                </div>
            </div>
            <%--联系人区--%>
            <div class="content_felt_linkman">
                <%--搜索框--%>
                <div class="content_felt_linkman_find">
                    <input type="text" placeholder="查找联系人或群">
                    <img src="${ctx}/static/img/搜索.png">
                </div>
                <%--好友列表--%>
                <div class="content_felt_linkman_friend">
                    <%--聊天--%>
                    <div class="content_felt_linkman_friend_chat" id="friend_chat">
                        <%--联系人信息内容--%>

                    </div>
                    <%--通讯录--%>
                    <div class="content_felt_linkman_friend_Contact" id="friend_Contact" style="display: none;">
                        <%--字母导航--%>
                        <div class="letter_navigation">
                            <a href="#A" class="navigation" onclick="arrive(this)">A</a>
                            <a href="#B" class="navigation" onclick="arrive(this)">B</a>
                            <a href="#C" class="navigation" onclick="arrive(this)">C</a>
                            <a href="#D" class="navigation" onclick="arrive(this)">D</a>
                            <a href="#E" class="navigation" onclick="arrive(this)">E</a>
                            <a href="#F" class="navigation" onclick="arrive(this)">F</a>
                            <a href="#G" class="navigation" onclick="arrive(this)">G</a>
                            <a href="#H" class="navigation" onclick="arrive(this)">H</a>
                            <a href="#I" class="navigation" onclick="arrive(this)">I</a>
                            <a href="#J" class="navigation" onclick="arrive(this)">J</a>
                            <a href="#K" class="navigation" onclick="arrive(this)">K</a>
                            <a href="#L" class="navigation" onclick="arrive(this)">L</a>
                            <a href="#M" class="navigation" onclick="arrive(this)">M</a>
                            <a href="#N" class="navigation" onclick="arrive(this)">N</a>
                            <a href="#O" class="navigation" onclick="arrive(this)">O</a>
                            <a href="#P" class="navigation" onclick="arrive(this)">P</a>
                            <a href="#Q" class="navigation" onclick="arrive(this)">Q</a>
                            <a href="#R" class="navigation" onclick="arrive(this)">R</a>
                            <a href="#S" class="navigation" onclick="arrive(this)">S</a>
                            <a href="#T" class="navigation" onclick="arrive(this)">T</a>
                            <a href="#U" class="navigation" onclick="arrive(this)">U</a>
                            <a href="#V" class="navigation" onclick="arrive(this)">V</a>
                            <a href="#W" class="navigation" onclick="arrive(this)">W</a>
                            <a href="#X" class="navigation" onclick="arrive(this)">X</a>
                            <a href="#Y" class="navigation" onclick="arrive(this)">Y</a>
                            <a href="#Z" class="navigation" onclick="arrive(this)">Z</a>
                            <a href="#a1" class="navigation" onclick="arrive(this)">\#</a>
                        </div>
                        <div class="linkman_list" id="linkman_list">
                            <%--添加好友提示信息--%>
                            <div class="HintFriend" id="HintFriend" onclick="examineFriendHint()">
                                <p>新朋友</p>
                                <input type="text" readonly value="0" style="display: none;">
                            </div>
                            <%--ABC等各个区域的联系人--%>
                            <div class="area" style="display: none;" id="A">
                                <p>A</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="B">
                                <p>B</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="C">
                                <p>C</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="D">
                                <p>D</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="E">
                                <p>E</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="F">
                                <p>F</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="G">
                                <p>G</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="H">
                                <p>H</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="I">
                                <p>I</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="J">
                                <p>J</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="K">
                                <p>K</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="L">
                                <p>L</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="M">
                                <p>M</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="N">
                                <p>N</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="O">
                                <p>O</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="P">
                                <p>P</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="Q">
                                <p>Q</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="R">
                                <p>R</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="S">
                                <p>S</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="T">
                                <p>T</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="U">
                                <p>U</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="V">
                                <p>V</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="W">
                                <p>W</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="X">
                                <p>X</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="Y">
                                <p>Y</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="Z">
                                <p>Z</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                            <div class="area" style="display: none;" id="a1">
                                <p>#</p>
                                <div class="linkman_list_list">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--底部切换按钮--%>
            <div class="content_felt_cut clearfix">
                <div class="content_felt_cut_chat cut" onclick="cutPage(this)">
                    <input type="text" value="1" hidden>
                    <img src="${ctx}/static/img/聊天s.png">
                    <span>聊天</span>
                </div>
                <div class="content_felt_cut_linkman cut" onclick="cutPage(this)">
                    <input type="text" value="2" hidden>
                    <img src="${ctx}/static/img/通讯录.png">
                    <span>通讯录</span>
                </div>
            </div>
        </div>
        <%--聊天内容显示区--%>
        <div class="content_right">
            <div class="chat_content" id="chat_content" style="display: none">
                <%--主键--%>
                <input type="text" hidden value="0" id="majorKeyId">
                <%--聊天状态--%>
                <input type="text" hidden value="0" id="stateCode">
                <%--文件--%>
                <input type="file" hidden id="pictureAndVideo" onchange="alters()">
                <%--头部--%>
                <div class="chat_content_head">
                    <p id="friend_name_or_group_name"></p>
                    <img src="${ctx}/static/img/更多功能-my.png">
                </div>
                <%--信息显示区--%>
                <div class="chat_content_body" id="chat_content_body">

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
        </div>
    </div>
    <%--弹出层--%>
    <%--点击加号创建群聊--%>
    <div class="addGroupPage" style="display: none;" id="addGroupPage">
        <div class="addGroupPageContent">
            <div class="addGroupPageHead">
                <p>
                    <span onclick="queryFriends(this)" class="asc">添加好友</span>
                    <span onclick="newGroupChat(this)" class="asc">新建群聊</span>
                </p>
                <i onclick="closeGroupPage()"></i>
            </div>
            <div class="addGroupPageFriend clearfix" id="addGroupPageFriend">
                <div class="addGroupPageBigBox" id="addGroupPageBigBox" style="left: 0%;">
                    <div class="queryFriends">
                        <div class="inquireFriend">
                            <input type="text" placeholder="请输入对方账号" id="friendNumber">
                            <img src="${ctx}/static/img/搜索.png" onclick="queryFriend()">
                        </div>
                        <div class="friendDataBox" id="friendDataBox">

                        </div>
                    </div>
                    <div class="newGroupChat" >
                        <div class="GroupChatName">
                            <label>群名称:</label>
                            <input type="text" placeholder="请输入群名称" id="GroupChatName">
                        </div>
                        <div class="newGroupChatBox" id="newGroupChatBox"></div>
                    </div>
                </div>
            </div>
            <div class="establishGroup" style="display: none;" id="establishGroup">
                <button type="button" onclick="establishGroup()">创建</button>
            </div>
        </div>
    </div>
    <%--查看朋友的验证信息弹出层--%>
    <div class="newFriendHint" id="newFriendHint" style="display: none;">
        <div class="newFriendHintContent">
            <div class="newFriendHintHead">
                <p>你收到的好友请求</p>
                <i onclick="closeNewFriendHint()"></i>
            </div>
            <div class="friendDataBox" id="friendDataBoxs">

            </div>
        </div>
    </div>
    <%--通讯录右键弹出好友信息--%>
    <div class="rightFriendData" id="rightFriendData" style="display: none;">
        <div class="rightFriendDataBody" id="rightFriendDataBody" style="display: block;">
            <%--头像区--%>
            <div class="rightFriendDataBody_head" id="rightFriendDataBody_head">
                <i class="rightFriendDataBody_head_close" onclick="closeRightFriendData()"></i>
            </div>
            <%--介绍区--%>
            <div class="rightFriendDataBody_body">
                <textarea class="rightFriendDataBody_body_textarea" readonly id="rightFriendDataBody_body_textarea"></textarea>
                <hr>
                <div class="rightFriendDataBody_body_data">
                    <label>&nbsp;热&nbsp;点&nbsp;号&nbsp;:</label>
                    <input value="super007" readonly id="hotUserName">
                </div>
                <div class="rightFriendDataBody_body_data">
                    <label>&nbsp;用户状态:</label>
                    <input value="离开" readonly id="userState">
                </div>
            </div>
            <%--按钮区--%>
            <div class="rightFriendDataBody_button clearfix" id="rightFriendDataBody_button">

            </div>
        </div>
        <%--分享好友弹出选择好友列表--%>
        <div class="friendList" id="friendList" style="display: none;">
            <p class="friendList_head"><i onclick="colseShareList()"></i>分享给</p>
            <div class="friendList_body" id="friendList_body"></div>
        </div>
    </div>
<script src="${ctx}/static/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/static/js/lib/layui/layui.js" charset="UTF-8"></script>
<script src="${ctx}/static/js/homeStyle.js" charset="UTF-8"></script>
<script>
    var ws;
    var group = [];//创建群聊勾选的用户集合
    var userId = ${sysUserVo.getId()};
    var majorKeyIds=0;//和好友聊天时记录他们的关系Id
    var stateCodes=0;
    if(WebSocket){
        ws = new WebSocket("wss://192.168.191.1:8443/hotSpot/homeWebSocket");
    }else {
        alert("你的浏览器不支持webSocket");
    }
    $(function (){
        document.oncontextmenu = function () {
            return false
        };
        //加载聊天信息页面
        friendChatLoad();
        //加载通讯录页面
        friendLoad();
    })
    /*加载聊天信息页面*/
    function friendChatLoad(){
        //加载好友聊天信息
        $.post("${ctx}/home/friendChatLoad",{},function (jsonData){
            var friend_chat = document.getElementById("friend_chat");
            var friend_chat_child = friend_chat.children;
            for(var i = friend_chat_child.length-1;i>=0;i--){
                friend_chat.removeChild(friend_chat_child[i])
            }
            for(var i = 0;i<jsonData.length;i++){
                var data = dataTime(jsonData[i].sendDate);
                if (jsonData[i].messageType==1){
                    if(jsonData[i].messageCount>0){
                        friend_chat.innerHTML+='<div class="contact_data clearfix" oncontextmenu="winRight(this)" onclick="openFriendChat(this,'+jsonData[i].majorKeyId+',1)"><input type="text" value="'+jsonData[i].majorKeyId+'" hidden><div class="contact_data_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+jsonData[i].portraitFrame+'"><div class="content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+jsonData[i].portrait+'"></div></div><div class="contact_data_name"><p>'+jsonData[i].nickName+'</p><div class="chatText">'+alterText(jsonData[i].chatText)+'</div></div><div class="contact_data_time"><p>'+data+'</p><input type="text" readonly value="'+jsonData[i].messageCount+'"></div></div>';

                    }else {
                        friend_chat.innerHTML+='<div class="contact_data clearfix" oncontextmenu="winRight(this)" onclick="openFriendChat(this,'+jsonData[i].majorKeyId+',1)"><input type="text" value="'+jsonData[i].majorKeyId+'" hidden><div class="contact_data_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+jsonData[i].portraitFrame+'"><div class="content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+jsonData[i].portrait+'"></div></div><div class="contact_data_name"><p>'+jsonData[i].nickName+'</p><div class="chatText">'+alterText(jsonData[i].chatText)+'</div></div><div class="contact_data_time"><p>'+data+'</p><input type="text" readonly value="'+jsonData[i].messageCount+'" hidden></div></div>';

                    }
                }else {
                    if (jsonData[i].messageCount>0){
                        var oneDiv = document.createElement('div');
                        oneDiv.className="contact_data_content_portrait";
                        var dataObj = jsonData[i].memberPortrait;
                        for (var j = 0;j<dataObj.length;j++){
                            oneDiv.innerHTML+='<img src="${ctx}/login/getPortraitImage?imgName='+dataObj[j]+'">';
                        }
                        friend_chat.innerHTML +='<div class="contact_group_data clearfix" oncontextmenu="winRight(this)" id="contact_data'+jsonData[i].majorKeyId+'" onclick="openGroupChat(this,'+jsonData[i].majorKeyId+',2)"><input type="text" value="'+jsonData[i].majorKeyId+'" hidden></div>';

                        var bigDiv = document.getElementById('contact_data'+jsonData[i].majorKeyId+'');
                        var tooDiv = document.createElement('div');
                        var threeDiv = document.createElement('div');
                        tooDiv.className="contact_data_name";
                        threeDiv.className="contact_data_time";
                        tooDiv.innerHTML='<p>'+jsonData[i].nickName+'</p><div class="chatText">'+alterText(jsonData[i].chatText)+'</div>';
                        threeDiv.innerHTML='<p>'+data+'</p><input type="text" readonly value="'+jsonData[i].messageCount+'">';
                        bigDiv.appendChild(oneDiv);
                        bigDiv.appendChild(tooDiv);
                        bigDiv.appendChild(threeDiv);
                    }else {
                        var oneDiv = document.createElement('div');
                        oneDiv.className="contact_data_content_portrait";
                        var dataObj = jsonData[i].memberPortrait;
                        for (var j = 0;j<dataObj.length;j++){
                            oneDiv.innerHTML+='<img src="${ctx}/login/getPortraitImage?imgName='+dataObj[j]+'">';
                        }
                        friend_chat.innerHTML +='<div class="contact_group_data clearfix" oncontextmenu="winRight(this)" id="contact_data'+jsonData[i].majorKeyId+'" onclick="openGroupChat(this,'+jsonData[i].majorKeyId+',2)"><input type="text" value="'+jsonData[i].majorKeyId+'" hidden></div>';

                        var bigDiv = document.getElementById('contact_data'+jsonData[i].majorKeyId+'');
                        var tooDiv = document.createElement('div');
                        var threeDiv = document.createElement('div');
                        tooDiv.className="contact_data_name";
                        threeDiv.className="contact_data_time";
                        tooDiv.innerHTML='<p>'+jsonData[i].nickName+'</p><div class="chatText">'+alterText(jsonData[i].chatText)+'</div>';
                        threeDiv.innerHTML='<p>'+data+'</p>';
                        bigDiv.appendChild(oneDiv);
                        bigDiv.appendChild(tooDiv);
                        bigDiv.appendChild(threeDiv);
                    }
                }

            }
        })
    }
    /*加载通讯录页面*/
    function friendLoad(){
        $.post("${ctx}/home/friendLoad",{},function (jsonData){
            var area = document.getElementsByClassName("area");
            for(var i = 0;i<area.length;i++){
                var areaChild = area[i].lastElementChild;
                var Child = areaChild.children;
                for(var j = Child.length-1;j>=0;j--){
                    areaChild.removeChild(Child[j])
                }
            }
            var figure = /^[0-9]*$/;
            var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]/im;
            for(var i = 0;i<jsonData.length;i++){
                var spell = (jsonData[i].spell).substr(0,1);
                if (spell==="A"||spell==="a"){
                    var A = document.getElementById("A");
                    opsition(jsonData[i],A);
                }
                if (spell==="B"||spell==="b"){
                    var B = document.getElementById("B");
                    opsition(jsonData[i],B);
                }
                if (spell==="C"||spell==="c"){
                    var C = document.getElementById("C");
                    opsition(jsonData[i],C);
                }
                if (spell==="D"||spell==="d"){
                    var D = document.getElementById("D");
                    opsition(jsonData[i],D);
                }
                if (spell==="E"||spell==="e"){
                    var E = document.getElementById("E");
                    opsition(jsonData[i],E);
                }
                if (spell==="F"||spell==="f"){
                    var F = document.getElementById("F");
                    opsition(jsonData[i],F);
                }
                if (spell==="G"||spell==="g"){
                    var G = document.getElementById("G");
                    opsition(jsonData[i],G);
                }
                if (spell==="H"||spell==="h"){
                    var H = document.getElementById("H");
                    opsition(jsonData[i],H);
                }
                if (spell==="I"||spell==="i"){
                    var I = document.getElementById("I");
                    opsition(jsonData[i],I);
                }
                if (spell==="J"||spell==="j"){
                    var J = document.getElementById("J");
                    opsition(jsonData[i],J);
                }
                if (spell==="K"||spell==="k"){
                    var K = document.getElementById("K");
                    opsition(jsonData[i],K);
                }
                if (spell==="L"||spell==="l"){
                    var L = document.getElementById("L");
                    opsition(jsonData[i],L);
                }
                if (spell==="M"||spell==="m"){
                    var M = document.getElementById("M");
                    opsition(jsonData[i],M);
                }
                if (spell==="N"||spell==="n"){
                    var N = document.getElementById("N");
                    opsition(jsonData[i],N);
                }
                if (spell==="O"||spell==="o"){
                    var O = document.getElementById("O");
                    opsition(jsonData[i],O);
                }
                if (spell==="P"||spell==="p"){
                    var P = document.getElementById("P");
                    opsition(jsonData[i],P);
                }
                if (spell==="Q"||spell==="q"){
                    var Q = document.getElementById("Q");
                    opsition(jsonData[i],Q);
                }
                if (spell==="R"||spell==="r"){
                    var R = document.getElementById("R");
                    opsition(jsonData[i],R);
                }
                if (spell==="S"||spell==="s"){
                    var S = document.getElementById("S");
                    opsition(jsonData[i],S);
                }
                if (spell==="T"||spell==="t"){
                    var T = document.getElementById("T");
                    opsition(jsonData[i],T);
                }
                if (spell==="U"||spell==="u"){
                    var U = document.getElementById("U");
                    opsition(jsonData[i],U);
                }
                if (spell==="V"||spell==="v"){
                    var V = document.getElementById("V");
                    opsition(jsonData[i],V);
                }
                if (spell==="W"||spell==="w"){
                    var W = document.getElementById("W");
                    opsition(jsonData[i],W);
                }
                if (spell==="X"||spell==="x"){
                    var X = document.getElementById("X");
                    opsition(jsonData[i],X);
                }
                if (spell==="Y"||spell==="y"){
                    var Y = document.getElementById("Y");
                    opsition(jsonData[i],Y);
                }
                if (spell==="Z"||spell==="z"){
                    var Z = document.getElementById("Z");
                    opsition(jsonData[i],Z);
                }
                if(figure.test(spell)||patrn.test(spell)){
                    var a1 = document.getElementById("a1");
                    opsition(jsonData[i],a1);
                }
            }
        })
        $.post("${ctx}/home/newFriendHint",{},function (jsonData){
            if(jsonData!=null){
                var messageCount = jsonData.messageCount;
                if (messageCount>0){
                    var HintFriend = document.getElementById("HintFriend");
                    HintFriend.lastElementChild.value =messageCount;
                    HintFriend.lastElementChild.style.display="block";
                }
            }
        })
    }
    ws.onmessage=function (event){
        var obj = JSON.parse(event.data);
        if (obj.state==1){
            if (obj.sendUserId ==userId){
                layer.msg(obj.msg);
            }else {
                var HintFriend = document.getElementById("HintFriend");
                var HintFriendChild = HintFriend.lastElementChild;
                HintFriendChild.value = obj.count
                HintFriendChild.style.display="block";
            }
        }
        if (obj.state==2){
            if (obj.sendUserId ==userId){
                layer.msg(obj.msg);
                friendLoad();
                examineFriendHint();
            }
        }
        if (obj.state==3){
            if (obj.sendUserId ==userId){
                layer.msg(obj.msg);
                closeGroupPage();
            }
            if (obj.ok){
                var friend_chat = document.getElementById("friend_chat");
                var oneDiv = document.createElement('div');
                oneDiv.className="contact_data_content_portrait";
                var nowDate = new Date();
                var hour = nowDate.getHours()>9?nowDate.getHours():"0"+nowDate.getHours();
                var minute = nowDate.getMinutes()>9?nowDate.getMinutes():"0"+nowDate.getMinutes();
                var dataObj = obj.data;
                for (var i = 0;i<dataObj.memberPortrait.length;i++){
                    oneDiv.innerHTML+='<img src="${ctx}/login/getPortraitImage?imgName='+dataObj.memberPortrait[i]+'">';
                }
                friend_chat.innerHTML ='<div class="contact_group_data clearfix" oncontextmenu="winRight(this)" id="contact_data'+dataObj.id+'" onclick="openGroupChat(this,'+dataObj.id+',2)"></div>'+friend_chat.innerHTML;

                var bigDiv = document.getElementById('contact_data'+dataObj.id+'');
                var oneInput = document.createElement('input');
                var tooDiv = document.createElement('div');
                var threeDiv = document.createElement('div');
                tooDiv.className="contact_data_name";
                threeDiv.className="contact_data_time";
                tooDiv.innerHTML='<p>'+dataObj.groupName+'</p><p>'+dataObj.groupText+'</p>';
                threeDiv.innerHTML='<p>'+hour+':'+minute+'</p>';
                oneInput.value=dataObj.id;
                oneInput.style.display = "none";
                bigDiv.appendChild(oneInput);
                bigDiv.appendChild(oneDiv);
                bigDiv.appendChild(tooDiv);
                bigDiv.appendChild(threeDiv);
            }
        }
        if (obj.state==4){
            /*判断信息是否发送成功*/
            if (obj.noProblem){
                friendChatLoad();
                /*选中当前聊天的*/
                //判断主键是否与当前的一致,是就添加信息否则不动
                /*判断发送人是否等于当前用户*/
                var sendDataText = obj.chatContent;
                var sendStr = sendDataText.replace(/\[em_([0-9]*)\]/g,'<img src="/hotSpot/static/img/face/'+'$1'+'.gif">');
                var div1 = document.createElement("div");
                var majorKeyId = document.getElementById("majorKeyId").value;
                var chat_content_body= document.getElementById("chat_content_body");
                /*延时器作用是在刷新后选中当前聊天的用户*/
                if (obj.stateCode==1&&obj.stateCode==stateCodes){
                    var contact_data = document.getElementsByClassName("contact_data");
                    setTimeout(function (){
                        for (var i = 0;i<contact_data.length;i++){
                            var friendId = contact_data[i].firstElementChild.value;
                            if (friendId==obj.sendId||friendId==obj.opposite){
                                contact_data[i].style.background="#8D8D8D";
                            }
                        }
                    },100)
                    if(obj.majorKeyId==majorKeyId){
                        //判断是否是发送图片还是文字
                        if(obj.chatWay==1){
                            if (userId==obj.sendId){
                                //执行添加一条聊天记录,在右边,并且
                                div1.className="messageMy clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="my_sendDataMessage">'+sendStr+'</div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }else{
                                //执行添加一条聊天记录,在左边
                                div1.className="messageFriend clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="sendDataMessage">'+sendStr+'</div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }
                        }
                        /*发送图片*/
                        if (obj.chatWay==2){
                            if (userId==obj.sendId){
                                //执行添加一条聊天记录,在右边,并且
                                div1.className="messageMy clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+sendStr+'"></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }else{
                                //执行添加一条聊天记录,在左边
                                div1.className="messageFriend clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+sendStr+'"></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }
                        }

                    }
                }else if (obj.stateCode==2&&obj.stateCode==stateCodes){
                    var contact_group_data = document.getElementsByClassName("contact_group_data");
                    setTimeout(function (){
                        for (var i = 0;i<contact_group_data.length;i++){
                            var friendId = contact_group_data[i].firstElementChild.value;
                            if (friendId==obj.majorKeyId){
                                contact_group_data[i].style.background="#8D8D8D";
                            }
                        }
                    },100)
                    if(obj.majorKeyId==majorKeyId){
                        if (obj.chatWay==1){
                            if (userId==obj.sendId){
                                //执行添加一条聊天记录,在右边,并且
                                div1.className="messageMy clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="my_sendDataMessage">'+sendStr+'</div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }else {
                                //执行添加一条聊天记录,在左边
                                div1.className="messageFriend clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="sendDataMessage">'+sendStr+'</div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }
                        }
                        if (obj.chatWay==2){
                            if (userId==obj.sendId){
                                //执行添加一条聊天记录,在右边,并且
                                div1.className="messageMy clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+sendStr+'"></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }else {
                                //执行添加一条聊天记录,在左边
                                div1.className="messageFriend clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+sendStr+'"></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }
                        }

                    }
                }
            }else {
                if (userId==obj.sendId){
                    var chat_content= document.getElementById("chat_content");
                    chat_content.style.display="none";
                    layer.msg(obj.chatContent);
                    friendChatLoad();
                    friendLoad();
                }
            }
        }
        if (obj.state==5){
            /*判断信息是否发送成功*/
            if (obj.noProblem){
                friendChatLoad();
                /*选中当前聊天的*/
                //判断主键是否与当前的一致,是就添加信息否则不动
                /*判断发送人是否等于当前用户*/
                if (userId==obj.sendId){
                    layer.msg("分享成功");
                }
                var div1 = document.createElement("div");
                var majorKeyId = document.getElementById("majorKeyId").value;
                var chat_content_body= document.getElementById("chat_content_body");
                /*延时器作用是在刷新后选中当前聊天的用户*/
                if (obj.stateCode==1&&obj.stateCode==stateCodes){
                    var contact_data = document.getElementsByClassName("contact_data");
                    setTimeout(function (){
                        for (var i = 0;i<contact_data.length;i++){
                            var friendId = contact_data[i].firstElementChild.value;
                            if (friendId==obj.sendId||friendId==obj.opposite){
                                contact_data[i].style.background="#8D8D8D";
                            }
                        }
                    },100)
                    if(obj.majorKeyId==majorKeyId){
                        //判断是否是发送图片还是文字
                        if(obj.chatWay==3){
                            if (userId!=obj.sendId){
                                //执行添加一条聊天记录,在左边
                                div1.className="messageFriend clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="sendDataMessage" onclick="openBusinessCard('+obj.opposite+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+obj.shareFriendPortrait+'"><h5>'+obj.shareFriendName+'</h5><br><h6>'+obj.shareFriendUserName+'</h6></div><hr><h6>个人名片</h6></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }else {
                                div1.className="messageMy clearfix";
                                div1.innerHTML+='<p>'+obj.nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="my_sendDataMessage" onclick="openBusinessCard('+obj.opposite+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+obj.shareFriendPortrait+'"><h5>'+obj.shareFriendName+'</h5><br><h6>'+obj.shareFriendUserName+'</h6></div><hr><h6>个人名片</h6></div>';
                                chat_content_body.appendChild(div1);
                                chat_content_body.scrollTop = chat_content_body.scrollHeight;
                            }
                        }
                    }
                }
            }else {
                if (userId==obj.sendId){
                    layer.msg(obj.chatContent);
                }
            }
        }
    }
    /*查询好友信息按钮*/
    function queryFriend(){
        var friend = document.getElementById("friendNumber");
        var friendDataBox = document.getElementById("friendDataBox");
        var friendNumber = friend.value;
        if (friendNumber==null||friendNumber==""){
            layer.msg("请输入需要查询的账号")
            return;
        }
        $.post("/hotSpot/home/queryFriend",{friendNumber:friendNumber},function (jsonData){
            var friendDataBoxChildren = friendDataBox.children;
            for(var i = friendDataBoxChildren.length-1;i>=0;i--){
                friendDataBox.removeChild(friendDataBoxChildren[i]);
            }
            if (jsonData.state){
                var obj = jsonData.data;
                friendDataBox.innerHTML+="<div class=\"friendData\" style=\"background: url('${ctx}/login/getPortraitImage?imgName="+obj.background+"');background-size: 100%;\"><div class=\"friend_head\"><img src=\"${ctx}/login/getPortraitImage?imgName="+obj.portraitFrame+"\"><div class=\"friend_portrait\"><img src=\"${ctx}/login/getPortraitImage?imgName="+obj.portrait+"\"></div></div><div class=\"friend_name\"><p>"+obj.nickName+"</p><p>"+obj.epitaph+"</p></div><div class=\"friend_add\"><button type=\"button\" onclick=\"newFriend("+obj.userId+")\">添加</button></div></div>";
                friend.value="";
            }else {
                layer.msg(jsonData.msg);
                friend.value="";
            }
        })
    }
    /*查看好友验证信息*/
    function examineFriendHint(){
        var newFriendHint = document.getElementById("newFriendHint");
        var friendDataBox = document.getElementById("friendDataBoxs");
        newFriendHint.style.display="block";
        var HintFriend =document.getElementById("HintFriend").lastElementChild;
        HintFriend.style.display="none";
        var friendDataBoxChildren = friendDataBox.children;
        for(var i = friendDataBoxChildren.length-1;i>=0;i--){
            friendDataBox.removeChild(friendDataBoxChildren[i]);
        }
        $.post("${ctx}/home/examineFriend",{},function (jsonData){
            for (var i = 0;i<jsonData.length;i++){
                friendDataBox.innerHTML+="<div class=\"friendData\" style=\"background: url('${ctx}/login/getPortraitImage?imgName="+jsonData[i].background+"');background-size: 100%;\"><div class=\"friend_head\"><img src=\"${ctx}/login/getPortraitImage?imgName="+jsonData[i].portraitFrame+"\"><div class=\"friend_portrait\"><img src=\"${ctx}/login/getPortraitImage?imgName="+jsonData[i].portrait+"\"></div></div><div class=\"friend_name\"><p>"+jsonData[i].nickName+"</p><p>"+jsonData[i].epitaph+"</p></div><div class=\"friend_add\"><button type=\"button\" onclick=\"consentFriend("+jsonData[i].userId+")\">同意</button></div></div>";
            }
        })
    }
    /*同意好友申请*/
    function consentFriend(friendId){
        var sendObj ={
            state:2,
            friendId:friendId,
        }
        ws.send(JSON.stringify(sendObj));
    }
    /*创建群聊*/
    function establishGroup(){
        var groupName = document.getElementById("GroupChatName");
        if (group.length==0){
            layer.msg("群成员不能为空");
            return;
        }
        if (groupName.value==null||groupName.value==undefined||groupName.value==""){
            layer.msg("群名称不能为空");
            return;
        }
        var sendObj = {
            state:3,
            sendId:userId,
            groupName:groupName.value,
            groupMember:group
        }
        ws.send(JSON.stringify(sendObj));
    }
    /*打开群聊信息*/
    function openGroupChat(o,majorKeyId,state){
        majorKeyIds=0;
        var chat_content = document.getElementById("chat_content");
        var chat_content_body = document.getElementById("chat_content_body");
        // chat_content_body.load();
        var chat_content_body_children = chat_content_body.children;
        for (var i = chat_content_body_children.length-1;i>=0;i--){
            chat_content_body.removeChild(chat_content_body_children[i]);
        }
        /*回填聊天记录*/
        $.post("${ctx}/home/backGroupFill",{majorKeyId:majorKeyId,page:0},function (obj){
            if(obj.length==15){
                chat_content_body.innerHTML+='<div class="historyRecord" id="historyRecord" onclick="GroupHistoryRecord('+majorKeyId+',15)">查看历史信息</div>';
            }
            for (var i = 0;i<obj.length;i++){
                var div1 = document.createElement("div");
                if (obj[i].sendId==userId){
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                }else {
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                }
            }
            chat_content_body.scrollTop = chat_content_body.scrollHeight;
        });
        /*改变当前和谁聊天的状态*/
        $.post("${ctx}/home/alterState",{majorKeyId:majorKeyId,state:state},function (jsonData){
            if (jsonData.state){
                var obj = jsonData.data;
                var contact_data = document.getElementsByClassName("contact_data");
                var contact_group_data = document.getElementsByClassName("contact_group_data");
                for (var i = 0;i<contact_data.length;i++){
                    contact_data[i].style.background="rgba(0,0,0,0.4)";
                }
                for (var i = 0;i<contact_group_data.length;i++){
                    contact_group_data[i].style.background="rgba(0,0,0,0.4)";
                }
                o.style.background="#8D8D8D";
                o.lastElementChild.lastElementChild.style.display="none";
                document.getElementById("majorKeyId").value=obj.majorKeyId;
                document.getElementById("stateCode").value=obj.state;
                majorKeyIds=obj.majorKeyId;
                stateCodes=obj.state;
                document.getElementById("friend_name_or_group_name").innerText=obj.name;
            }
        })
        chat_content.style.display="block";
    }
    /*打开好友聊天*/
    function openFriendChat(o,majorKeyId,state){
        var chat_content = document.getElementById("chat_content");
        var chat_content_body = document.getElementById("chat_content_body");
        // chat_content_body.load();
        var chat_content_body_children = chat_content_body.children;
        for (var i = chat_content_body_children.length-1;i>=0;i--){
            chat_content_body.removeChild(chat_content_body_children[i]);
        }
        /*回填聊天记录*/
        $.post("${ctx}/home/backFill",{majorKeyId:majorKeyId,page:0},function (obj){
            if(obj.length==15){
                chat_content_body.innerHTML+='<div class="historyRecord" id="historyRecord" onclick="historyRecord('+majorKeyId+',15)">查看历史信息</div>';
            }
            for (var i = 0;i<obj.length;i++){
                var div1 = document.createElement("div");
                if (obj[i].sendId==userId){
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                    if(obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        //执行添加一条聊天记录,在左边
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage" onclick="openBusinessCard('+str[3]+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div><hr><h6>个人名片</h6></div>';
                        chat_content_body.appendChild(div1);
                        chat_content_body.scrollTop = chat_content_body.scrollHeight;
                    }
                }else {
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                    if(obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage" onclick="openBusinessCard('+str[3]+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div><hr><h6>个人名片</h6></div>';
                        chat_content_body.appendChild(div1);
                        chat_content_body.scrollTop = chat_content_body.scrollHeight;
                    }
                }
            }
            chat_content_body.scrollTop = chat_content_body.scrollHeight;
        });

        /*改变当前和谁聊天的状态*/
        $.post("${ctx}/home/alterState",{majorKeyId:majorKeyId,state:state},function (jsonData){
            if (jsonData.state){
                var obj = jsonData.data;
                var contact_data = document.getElementsByClassName("contact_data");
                var contact_group_data = document.getElementsByClassName("contact_group_data");
                for (var i = 0;i<contact_data.length;i++){
                    contact_data[i].style.background="rgba(0,0,0,0.4)";
                }
                for (var i = 0;i<contact_group_data.length;i++){
                    contact_group_data[i].style.background="rgba(0,0,0,0.4)";
                }
                o.style.background="#8D8D8D";
                o.lastElementChild.lastElementChild.style.display="none";
                document.getElementById("majorKeyId").value=obj.majorKeyId;
                document.getElementById("stateCode").value=obj.state;
                majorKeyIds=majorKeyId;
                stateCodes=obj.state;
                document.getElementById("friend_name_or_group_name").innerText=obj.name;
            }
        });
        chat_content.style.display="block";
    }
    /*通讯录打开好友操作界面*/
    function openFriendChats(majorKeyId){
        var rightFriendData = document.getElementById("rightFriendData");
        rightFriendData.style.display="block";
        var rightFriendDataBody_head = document.getElementById("rightFriendDataBody_head");
        var rightFriendDataBody_body_textarea = document.getElementById("rightFriendDataBody_body_textarea");
        var hotUserName = document.getElementById("hotUserName");
        var userState = document.getElementById("userState");
        var rightFriendDataBody_button = document.getElementById("rightFriendDataBody_button");
        $.post("${ctx}/home/friendData",{majorKeyId:majorKeyId},function (obj){
            rightFriendDataBody_head.setAttribute('style','background:url("${ctx}/login/getPortraitImage?imgName='+obj.background+'");background-size: 100%;');
            rightFriendDataBody_body_textarea.innerText=obj.epitaph;
            hotUserName.value=obj.userName;
            if(obj.mood==0){
                userState.value="未知";
            }
            if (obj.mood==1){
                userState.value="开心";
            }
            if (obj.mood==2){
                userState.value="伤心";
            }
            if (obj.mood==3){
                userState.value="游戏中";
            }
            if (obj.mood==4){
                userState.value="上班";
            }
            if (obj.mood==5){
                userState.value="美滋滋";
            }
            rightFriendDataBody_button.innerHTML ='<button onclick="openSendMessage('+obj.userId+')"><i id="send"></i>发送信息</button><button onclick="shareCallingCard('+obj.userId+')")"><i id="share"></i>分享名片</button><button onclick="deleteFriend('+obj.userId+')")"><i id="delete"></i>删除好友</button>';
            rightFriendDataBody_head.innerHTML ='<div class="content_felt_material_head"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="rightFriendDataBody_head_nickname">'+obj.nickName+'</div><i class="rightFriendDataBody_head_close" onclick="closeRightFriendData()"></i>'
        })

    }
    //打开发送信息窗口
    function openSendMessage(majorKeyId){
        var rightFriendData = document.getElementById("rightFriendData");
        rightFriendData.style.display="none";
        var chat_content = document.getElementById("chat_content");
        chat_content.style.display="block";
        var chat_content_body = document.getElementById("chat_content_body");
        // chat_content_body.load();
        var chat_content_body_children = chat_content_body.children;
        for (var i = chat_content_body_children.length-1;i>=0;i--){
            chat_content_body.removeChild(chat_content_body_children[i]);
        }
        /*回填聊天记录*/
        $.post("${ctx}/home/backFill",{majorKeyId:majorKeyId,page:0},function (obj){
            if(obj.length==15){
                chat_content_body.innerHTML+='<div class="historyRecord" id="historyRecord" onclick="historyRecord('+majorKeyId+',15)">查看历史信息</div>';
            }
            for (var i = 0;i<obj.length;i++){
                var div1 = document.createElement("div");
                if (obj[i].sendId==userId){
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在右边,并且
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        //执行添加一条聊天记录,在左边
                        div1.className="messageMy clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage" onclick="openBusinessCard('+str[3]+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div><hr><h6>个人名片</h6></div>';
                        chat_content_body.appendChild(div1);
                        chat_content_body.scrollTop = chat_content_body.scrollHeight;
                    }
                }else {
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage">'+alterText(obj[i].chatContent)+'</div>';
                        chat_content_body.appendChild(div1);
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div>';
                        chat_content_body.appendChild(div1);
                    }
                    if(obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        //执行添加一条聊天记录,在左边
                        div1.className="messageFriend clearfix";
                        div1.innerHTML+='<p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage" onclick="openBusinessCard('+str[3]+')"><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div><hr><h6>个人名片</h6></div>';
                        chat_content_body.appendChild(div1);
                        chat_content_body.scrollTop = chat_content_body.scrollHeight;
                    }
                }
            }
            chat_content_body.scrollTop = chat_content_body.scrollHeight;
        });
        /*改变当前和谁聊天的状态*/
        $.post("${ctx}/home/alterState",{majorKeyId:majorKeyId,state:1},function (jsonData){
            if (jsonData.state){
                var obj = jsonData.data;
                document.getElementById("majorKeyId").value=obj.majorKeyId;
                document.getElementById("stateCode").value=obj.state;
                majorKeyIds=majorKeyId;
                stateCodes=obj.state;
                document.getElementById("friend_name_or_group_name").innerText=obj.name;
            }
        })
    }
    //关闭右键通讯录
    function closeRightFriendData(){
        var rightFriendData = document.getElementById("rightFriendData");
        rightFriendData.style.display="none";
    }
    //分享名片
    function shareCallingCard(majorKeyId){
        var friendList = document.getElementById('friendList');
        var friendList_body = document.getElementById('friendList_body');
        var friendList_body_children = friendList_body.children;
        var rightFriendDataBody = document.getElementById('rightFriendDataBody');
        rightFriendDataBody.style.display='none';
        friendList.style.display='block';
        for (var i=friendList_body_children.length-1;i>=0;i--){
            friendList_body.removeChild(friendList_body_children[i]);
        }
        $.post("/hotSpot/home/selectGroupFriend",{},function (data){
            if (data!=null){
                for (var i = 0;i<data.length;i++){
                    friendList_body.innerHTML+='<div class="shareFriend_data clearfix" onclick="shareForFriend('+data[i].userId+','+majorKeyId+')"><div class="shareFriend_data_portrait"><img src="/hotSpot/login/getPortraitImage?imgName='+data[i].portrait+'" alt=""></div><div class="friend_data_name"><p>'+data[i].nickName+'</p></div></div>';
                }
            }
        })
    }
    /*修改个人信息区*/
    function personality(){

    }
    /*好友查看聊天记录*/
    function historyRecord(majorKeyId,page){
        var chat_content_body = document.getElementById("chat_content_body");
        var Top = chat_content_body.scrollHeight;
        /*回填聊天记录*/
        $.post("${ctx}/home/backFill",{majorKeyId:majorKeyId,page:page},function (obj){
            for (var i = 0;i<obj.length;i++){
                if (obj[i].sendId==userId){
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在右边,并且
                        chat_content_body.innerHTML ='<div class="messageMy clearfix"><p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage">'+alterText(obj[i].chatContent)+'</div></div>'+chat_content_body.innerHTML;
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在右边,并且
                        chat_content_body.innerHTML ='<div class="messageMy clearfix"><p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div></div>'+chat_content_body.innerHTML;
                    }
                    if(obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        chat_content_body.innerHTML ='<div class="messageMy clearfix"><p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage" onclick="openBusinessCard('+str[3]+')><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div><hr><h6>个人名片</h6></div></div>'+chat_content_body.innerHTML;
                    }
                }else {
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在左边
                        chat_content_body.innerHTML ='<div class="messageFriend clearfix"><p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage">'+alterText(obj[i].chatContent)+'</div></div>'+chat_content_body.innerHTML;
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在左边
                        chat_content_body.innerHTML ='<div class="messageFriend clearfix"><p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div></div>'+chat_content_body.innerHTML;
                    }
                    if(obj[i].chatWay==3){
                        var strContent = obj[i].chatContent;
                        var str = strContent.split(",");
                        chat_content_body.innerHTML ='<div class="messageFriend clearfix"><p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage" onclick="openBusinessCard('+str[3]+')><div class="businessCard clearfix"><img src="${ctx}/login/getPortraitImage?imgName='+str[0]+'"><h5>'+str[1]+'</h5><br><h6>'+str[2]+'</h6></div></div>'+chat_content_body.innerHTML;
                    }
                }
            }
            var historyRecord = document.getElementById("historyRecord");
            chat_content_body.removeChild(historyRecord);
            if(obj.length==15){
                page+=15
                chat_content_body.innerHTML='<div class="historyRecord" id="historyRecord" onclick="historyRecord('+majorKeyId+','+page+')">查看历史信息</div>'+chat_content_body.innerHTML;
            }
        });
    }
    /*群聊查看聊天记录*/
    function GroupHistoryRecord(majorKeyId,page){
        var chat_content_body = document.getElementById("chat_content_body");
        var Top = chat_content_body.scrollHeight;
        /*回填聊天记录*/
        $.post("${ctx}/home/backGroupFill",{majorKeyId:majorKeyId,page:page},function (obj){
            for (var i = 0;i<obj.length;i++){
                if (obj[i].sendId==userId){
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在右边,并且
                        chat_content_body.innerHTML ='<div class="messageMy clearfix"><p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage">'+alterText(obj[i].chatContent)+'</div></div>'+chat_content_body.innerHTML;
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在右边,并且
                        chat_content_body.innerHTML ='<div class="messageMy clearfix"><p>'+obj[i].nickName+'</p><div class="myDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="my_content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="my_sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div></div>'+chat_content_body.innerHTML;
                    }
                }else {
                    if (obj[i].chatWay==1){
                        //执行添加一条聊天记录,在左边
                        chat_content_body.innerHTML ='<div class="messageFriend clearfix"><p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage">'+alterText(obj[i].chatContent)+'</div></div>'+chat_content_body.innerHTML;
                    }
                    if (obj[i].chatWay==2){
                        //执行添加一条聊天记录,在左边
                        chat_content_body.innerHTML ='<div class="messageFriend clearfix"><p>'+obj[i].nickName+'</p><div class="friendDataPortrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portraitFrame+'"><div class="content_felt_material_head_portraits"><img src="${ctx}/login/getPortraitImage?imgName='+obj[i].portrait+'"></div></div><div class="sendDataMessage"><img src="${ctx}/chat/getPortraitImage?imgName='+alterText(obj[i].chatContent)+'"></div></div>'+chat_content_body.innerHTML;
                    }
                }
            }
            var historyRecord = document.getElementById("historyRecord");
            chat_content_body.removeChild(historyRecord);
            if(obj.length==15){
                page+=15
                chat_content_body.innerHTML='<div class="historyRecord" id="historyRecord" onclick="historyRecord('+majorKeyId+','+page+')">查看历史信息</div>'+chat_content_body.innerHTML;
            }
        });
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
        if ((pictureAndVideo==undefined||pictureAndVideo==null)&&(sendStr==undefined||sendStr==null||sendStr=="")){
            return;
        }
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
                            opposite:majorKeyIds,
                            stateCode:stateCode,
                            chatWay:2,
                            chatContent:jsonMsg.msg,
                            state:4,
                            sendId:userId,
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
            opposite:majorKeyIds,
            stateCode:stateCode,
            chatWay:1,
            chatContent:sendText,
            state:4,
            sendId:userId
        }
        ws.send(JSON.stringify(sendObj));
        dataTextSend.innerHTML="";
    }
    /*分享名片*/
    function shareForFriend(majorKeyId,shareFriendId){
        var rightFriendData = document.getElementById("rightFriendData");
        var rightFriendDataBody = document.getElementById("rightFriendDataBody");
        var friendList = document.getElementById("friendList");
        rightFriendData.style.display="none";
        friendList.style.display="none";
        rightFriendDataBody.style.display="block";
        var sendObj = {
            shareFriendId:shareFriendId,
            majorKeyId:majorKeyId,
            stateCode:1,
            chatWay:3,
            state:5,
            sendId:userId
        }
        ws.send(JSON.stringify(sendObj));
    }
    /*关闭分享列表*/
    function colseShareList(){
        var rightFriendDataBody = document.getElementById("rightFriendDataBody");
        var friendList = document.getElementById("friendList");
        friendList.style.display="none";
        rightFriendDataBody.style.display="block";
    }
    /*聊天记录中打开名片分享*/
    function openBusinessCard(majorKeyId){
        var rightFriendData = document.getElementById("rightFriendData");
        rightFriendData.style.display="block";
        var rightFriendDataBody_head = document.getElementById("rightFriendDataBody_head");
        var rightFriendDataBody_body_textarea = document.getElementById("rightFriendDataBody_body_textarea");
        var hotUserName = document.getElementById("hotUserName");
        var userState = document.getElementById("userState");
        var rightFriendDataBody_button = document.getElementById("rightFriendDataBody_button");
        $.post("${ctx}/home/openBusinessCard",{majorKeyId:majorKeyId},function (obj){
            rightFriendDataBody_head.setAttribute('style','background:url("${ctx}/login/getPortraitImage?imgName='+obj.background+'");background-size: 100%;');
            rightFriendDataBody_body_textarea.innerText=obj.epitaph;
            hotUserName.value=obj.userName;
            if(obj.mood==0){
                userState.value="未知";
            }
            if (obj.mood==1){
                userState.value="开心";
            }
            if (obj.mood==2){
                userState.value="伤心";
            }
            if (obj.mood==3){
                userState.value="游戏中";
            }
            if (obj.mood==4){
                userState.value="上班";
            }
            if (obj.mood==5){
                userState.value="美滋滋";
            }
            rightFriendDataBody_head.innerHTML ='<div class="content_felt_material_head"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portraitFrame+'"><div class="content_felt_material_head_portrait"><img src="${ctx}/login/getPortraitImage?imgName='+obj.portrait+'"></div></div><div class="rightFriendDataBody_head_nickname">'+obj.nickName+'</div><i class="rightFriendDataBody_head_close" onclick="closeRightFriendData()"></i>'
            if (obj.ok){
                rightFriendDataBody_button.innerHTML ='<button onclick="openSendMessage('+obj.userId+')"><i id="send"></i>发送信息</button><button onclick="shareCallingCard('+obj.userId+')")"><i id="share"></i>分享名片</button><button onclick="deleteFriend('+obj.userId+')")"><i id="delete"></i>删除好友</button>';
            }else {
                rightFriendDataBody_button.innerHTML ='<button style="width: 50%;" onclick="shareCallingCard('+obj.userId+')")"><i id="share"></i>分享名片</button><button style="width: 50%;" onclick="newFriend('+obj.userId+')")"><i id="add"></i>添加好友</button>';
            }
        })
    }
    /*删除好友*/
    function deleteFriend(majorKeyId){
        $.post("${ctx}/home/deleteFriend",{majorKeyId:majorKeyId},function (jsonData){
            if (jsonData.state){
                layer.msg(jsonData.msg);
                friendChatLoad();
                friendLoad();
                closeRightFriendData();
            }else {
                layer.msg(jsonData.msg)
            }
        })
    }
</script>
</body>
</html>
