
function cutPage(obj){
    var objStyle = obj.firstElementChild.value;
    var cutPage = document.getElementsByClassName("cut");
    var friend_Contact = document.getElementById("friend_Contact");
    var friend_chat = document.getElementById("friend_chat");
    if(objStyle==1){
        for (var i = 0;i<cutPage.length;i++){
            var cutPageObj = cutPage[i];
            var cutPageValue = cutPage[i].firstElementChild.value;
            var child = cutPageObj.children;
            if(cutPageValue==objStyle){
                child[1].setAttribute("src","/hotSpot/static/img/聊天s.png");
                child[2].style.color="#01AAED";
            }else {
                child[1].setAttribute("src","/hotSpot/static/img/通讯录.png");
                child[2].style.color="#FFFFFF";
            }
        }
        friend_Contact.style.display="none";
        friend_chat.style.display="block";
        friendChatLoad();
    }
    if(objStyle==2){
        for (var i = 0;i<cutPage.length;i++){
            var cutPageObj = cutPage[i];
            var cutPageValue = cutPage[i].firstElementChild.value;
            var child = cutPageObj.children;
            if(cutPageValue==objStyle){
                child[1].setAttribute("src","/hotSpot/static/img/通讯录s.png");
                child[2].style.color="#01AAED";
            }else {
                child[1].setAttribute("src","/hotSpot/static/img/聊天.png");
                child[2].style.color="#FFFFFF";
            }
        }
        friend_chat.style.display="none";
        friend_Contact.style.display="block";
        friendLoad()
    }

}
function winRight(obj){
    alert("右键点击事件触发");
    document.oncontextmenu = function () {
        return false
    };
}
function arrive(obj){
    var navigation = document.getElementsByClassName("navigation");
    for (var i = 0;i<navigation.length;i++){
        navigation[i].style.color="#FFFFFF";
    }
    obj.style.color="#01AAED";
}
function opsition(obj,spell){
    spell.style.display = "block";
    spell.lastElementChild.innerHTML+='<div class="linkman" oncontextmenu="openFriendChats('+obj.userId+',1)"><input type="text" value="'+obj.id+'" hidden><img src="/hotSpot/login/getPortraitImage?imgName='+obj.portrait+'"><p>'+obj.nickName+'</p></div>';
}
function queryFriends(obj){
    var addGroupPageBigBox = document.getElementById("addGroupPageBigBox");
    var establishGroup = document.getElementById("establishGroup");

    var asc = document.getElementsByClassName("asc");
    for (var i = 0;i<asc.length;i++){
        asc[i].style.borderBottom = "none";
    }
    var BigBoxF = -100;
    if(addGroupPageBigBox.style.left!=="0%"){
        establishGroup.style.display="none";
        var clock = window.setInterval(function (){
            if (BigBoxF==0){
                clearInterval(clock);
                obj.style.borderBottom = "2px solid #01AAED";
            }
            addGroupPageBigBox.style.left = BigBoxF+"%";
            BigBoxF++
        },1)
    }
}
function newGroupChat(obj){
    group = [];
    var addGroupPageBigBox = document.getElementById("addGroupPageBigBox");
    var groupName = document.getElementById("GroupChatName");
    groupName.value="";
    var newGroupChatBox = document.getElementById("newGroupChatBox");
    var asc = document.getElementsByClassName("asc");
    var checkbox = document.getElementsByClassName("checkbox");
    var establishGroup = document.getElementById("establishGroup");
    var newGroupChatBoxChildren = newGroupChatBox.children;
    for (var i = newGroupChatBoxChildren.length-1;i>=0;i--){
        newGroupChatBox.removeChild(newGroupChatBoxChildren[i]);
    }
    for (var i = 0;i<checkbox.length;i++){
        checkbox[i].checked=false;
        checkbox[i].parentElement.parentElement.style.border="none";
    }
    for (var i = 0;i<asc.length;i++){
        asc[i].style.borderBottom = "none";
    }
    var BigBoxF = -1;
    $.post("/hotSpot/home/selectGroupFriend",{},function (data){
        if (data!=null){
            for (var i = 0;i<data.length;i++){
                newGroupChatBox.innerHTML+='<div class="friend_data clearfix"><div class="friend_data_portrait"><img src="/hotSpot/login/getPortraitImage?imgName='+data[i].portrait+'" alt=""></div><div class="friend_data_name"><p>'+data[i].nickName+'</p></div><div class="friend_data_button"><input type="checkbox" value="'+data[i].userId+'" onclick="checkboxOnclick(this)" class="checkbox"></div></div>';
            }
        }
    })
    if(addGroupPageBigBox.style.left!=="-100%"){
        var clock = window.setInterval(function (){
            if (BigBoxF==-100){
                clearInterval(clock);
                obj.style.borderBottom = "2px solid #01AAED";
                establishGroup.style.display="block";
            }
            addGroupPageBigBox.style.left = BigBoxF+"%";
            BigBoxF--
        },1)
    }
}
/*div插入图片*/
function addText(o){
    var obj = $("#dataTextSend");
    var range, node;
    /*获取到输入框的光标*/
    if(!obj.hasfocus) {
        obj.focus();
    }
    var text = '<img src="/hotSpot/static/img/face/'+o+'.gif">';
    var range, node;
    if(window.getSelection && window.getSelection().getRangeAt) {
        range = window.getSelection().getRangeAt(0);
        range.collapse(false);
        node = range.createContextualFragment(text);
        var c = node.lastChild;
        range.insertNode(node);
        if(c) {
            range.setEndAfter(c);
            range.setStartAfter(c);
        }
        var j = window.getSelection();
        j.removeAllRanges();
        j.addRange(range);
    } else if(document.selection && document.selection.createRange) {
        document.selection.createRange().pasteHTML(text);
    }
}
/*文件input标签改变事件*/
regexImageFilter = /^(?:image\/bmp|image\/gif|image\/jpg|image\/jpeg|image\/png)$/i;
var imgReader = new FileReader();
function alters(){
    var pictureAndVideo = document.getElementById("pictureAndVideo").files[0];
    if (regexImageFilter.test(pictureAndVideo.type)) {
        imgReader.readAsDataURL(pictureAndVideo);
    }else {
        layer.msg("你选择的不是一个图片文件")
    }
}
imgReader.onload=function (ev){
    var imgAndVidShow = document.getElementById("imgAndVidShow");
    imgAndVidShow.style.display="block";
    imgAndVidShow.lastElementChild.setAttribute("src",ev.target.result);
}


/*----------------------------------------*/
/*打开添加按钮*/
function addGroup(){
    var addGroupPage = document.getElementById("addGroupPage");
    addGroupPage.style.display = "block";
}
/*关闭添加按钮*/
function closeGroupPage(){
    group = [];
    var addGroupPage = document.getElementById("addGroupPage");
    var groupName = document.getElementById("GroupChatName");
    var checkbox = document.getElementsByClassName("checkbox");
    groupName.value="";
    for (var i = 0;i<checkbox.length;i++){
        checkbox[i].checked=false;
        checkbox[i].parentElement.parentElement.style.border="none";
    }
    addGroupPage.style.display = "none";
}
/*聊天窗口最后一条信息回填*/
function alterText(str){
    if (str==null||str==""){
        return "";
    }
    var sendStr = str.replace(/\[em_([0-9]*)\]/g,'<img src="/hotSpot/static/img/face/'+'$1'+'.gif">');
    return sendStr;
}
/*处理时间*/
function dataTime(obj){
    if(obj!=null){
        var data = new Date(obj);
        var dataTime = new Date();
        if (data.getDate()==dataTime.getDate()&&data.getMonth()==dataTime.getMonth()){
            var Day = data.getMinutes()>9?data.getMinutes():"0"+data.getMinutes();
            return data.getHours()+":"+Day;
        }else if(dataTime.getDate()-data.getDate()==1){
            return "昨天";
        }else{
            return data.getMonth()+1+"月-"+data.getDate()+"日";
        }
    }else {
        return "&nbsp;";
    }
}
/*添加好友信息按钮*/
function newFriend(friendId){
    var sendObj = {
        state:1,
        friendId:friendId
    }
    ws.send(JSON.stringify(sendObj));
}
/*关闭好友验证信息*/
function closeNewFriendHint(){
    var newFriendHint = document.getElementById("newFriendHint");
    var friendDataBox = document.getElementById("friendDataBoxs");
    newFriendHint.style.display="none";
    var friendDataBoxChildren = friendDataBox.children;
    for(var i = friendDataBoxChildren.length-1;i>=0;i--){
        friendDataBox.removeChild(friendDataBoxChildren[i]);
    }
}
/*勾选中的好友*/
function checkboxOnclick(checkbox){
    var checkboxParent = checkbox.parentElement.parentElement;
    var checkboxValue = checkbox.value;
    if (checkbox.checked==true){
        checkboxParent.style.border = "1px solid #01AAED";
        group.push(checkboxValue);
    }else {
        checkboxParent.style.border = "none";
        var index = group.indexOf(checkboxValue);
        group.splice(index,1);
    }
}
/*--------------------------------------聊天---------------------------------*/
var j = 0;
$(function (){
    var expression = document.getElementById("expression");
    for (var i =1;i<75;i++){
        expression.innerHTML+='<img src="/hotSpot/static/img/face/'+i+'.gif" onclick="addText('+i+')">';
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
        obj.setAttribute("src","/hotSpot/static/img/表情s.png");
        expression.style.display="block";
        j++;
    }else {
        obj.setAttribute("src","/hotSpot/static/img/表情.png");
        expression.style.display="none";
        j++;
    }
}
/*关闭表情*/
function closeExpression(){
    document.getElementById("expression").style.display="none";
    document.getElementById("openExpression").setAttribute("src","/hotSpot/static/img/表情.png");
    j=0;
}
/*选择图片*/
function openFileChoice(){
    /*关闭表情包*/
    document.getElementById("expression").style.display="none";
    document.getElementById("openExpression").setAttribute("src","/hotSpot/static/img/表情.png");
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
