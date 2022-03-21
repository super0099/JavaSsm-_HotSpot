package com.yxm.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxm.common.GetUserSession;
import com.yxm.po.*;
import com.yxm.service.HomeService;

import com.yxm.util.ProjectParameter;
import com.yxm.vo.GroupDataVo;
import com.yxm.vo.SendDataAndUserData;
import com.yxm.vo.ShareFriendSend;
import com.yxm.vo.WebSocketObj;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ServerEndpoint(value = "/homeWebSocket",configurator = GetUserSession.class)
public class HomeWebSocket {
    private static final Map<Integer,HomeWebSocket> userMap = new ConcurrentHashMap<>();
    private SysUserVo sysUserVo;
    private Session session;
    private final ObjectMapper objectMapper = new ObjectMapper();
    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
    private HomeService homeService = (HomeService) webApplicationContext.getBean("homeServiceImpl");

    @OnOpen
    public void OnOpen(Session session,EndpointConfig config) throws IOException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.sysUserVo = (SysUserVo) httpSession.getAttribute(ProjectParameter.SESSION_USER);
        this.session =session;
        boolean IsOk = this.homeService.updateUserPage(sysUserVo.getId());
        userMap.put(sysUserVo.getId(),this);
    }
    @OnMessage
    public void OnMessage(String str) throws IOException {
        JSONObject jsonObject =JSONObject.fromObject(str);
        Integer state = jsonObject.getInt("state");
        if (state==1){
            AddFriend(str);
        }
        if(state==2){
            consentFriend(str);
        }
        if (state==3){
            establishGroup(str);
        }
        if (state==4){
            sendText(str);
        }
        if (state==5){
            shareForFriend(str);
        }
    }
    @OnError
    public void OnError(Throwable throwable){

    }
    @OnClose
    public void OnClose(){
        userMap.remove(sysUserVo.getId());
        boolean IsOk = this.homeService.updateUserPage(sysUserVo.getId());
    }

    private void AddFriend(String str) throws IOException {
        JSONObject jsonObject =JSONObject.fromObject(str);
        Integer friendId = jsonObject.getInt("friendId");
        List<Integer> userCount = new ArrayList<>();
        userCount.add(sysUserVo.getId());
        userCount.add(friendId);
        SysFriend sysFriend = new SysFriend();
        sysFriend.setaFriend(sysUserVo.getId());
        sysFriend.setbFriend(friendId);
        sysFriend.setState((byte) 3);
        WebSocketObj webSocketObj = new WebSocketObj();
        webSocketObj.setState(1);
        webSocketObj.setSendUserId(sysUserVo.getId());
        try {
            this.homeService.AddFriend(sysFriend);
            SysHint sysHint = this.homeService.newFriendHint(friendId);
            webSocketObj.setCount(sysHint.getMessageCount());
            webSocketObj.setMsg("好友请求已经发送");
            String strJson = objectMapper.writeValueAsString(webSocketObj);
            for (Integer key:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(key);
                for (Integer userId:userCount) {
                    if(key==userId){
                        homeWebSocket.session.getBasicRemote().sendText(strJson);
                    }
                }
            }
        }catch (RuntimeException | IOException e){
            webSocketObj.setMsg("添加失败");
            String strJson = objectMapper.writeValueAsString(webSocketObj);
            for (Integer key:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(key);
                for (Integer userId:userCount) {
                    if(key==userId){
                        homeWebSocket.session.getBasicRemote().sendText(strJson);
                    }
                }
            }
        }
    }
    private void consentFriend(String str) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer friendId = jsonObject.getInt("friendId");
        SysFriend sysFriend = new SysFriend();
        sysFriend.setbFriend(sysUserVo.getId());
        sysFriend.setaFriend(friendId);
        sysFriend.setState((byte) 1);
        List<Integer> user = new ArrayList<>();
        user.add(friendId);
        user.add(sysUserVo.getId());
        WebSocketObj webSocketObj = new WebSocketObj();
        webSocketObj.setState(2);
        webSocketObj.setSendUserId(sysUserVo.getId());
        try{
            boolean isOk = this.homeService.updateConsentFriend(sysFriend)>0;
            if (isOk){
                webSocketObj.setMsg("您已同意好友请求");
                String jsonStr = objectMapper.writeValueAsString(webSocketObj);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    for (Integer userId:user) {
                        if (key==userId){
                            homeWebSocket.session.getBasicRemote().sendText(jsonStr);
                        }
                    }
                }
            }
        }catch (RuntimeException e){
            webSocketObj.setMsg("操作发生异常");
            String jsonStr = objectMapper.writeValueAsString(webSocketObj);
            for (Integer key:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(key);
                for (Integer userId:user) {
                    if (key==userId){
                        homeWebSocket.session.getBasicRemote().sendText(jsonStr);
                    }
                }
            }
        }
    }
    private void establishGroup(String str) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray groupMemberId = jsonObject.getJSONArray("groupMember");
        List<Integer> groupMember = new ArrayList<>();
        groupMember.add(sysUserVo.getId());
        for (Object obj:groupMemberId) {
            Integer MemberId = Integer.parseInt(obj.toString());
            groupMember.add(MemberId);
        }
        Random random = new Random();
        String groupNumber = String.valueOf(random.nextInt(900000) + 100000);
        String newGroupNumber = newUserName(groupNumber);
        //根据群号去查询群Id
        String groupName = jsonObject.getString("groupName");
        WebSocketObj webSocketObj = new WebSocketObj();
        webSocketObj.setSendUserId(sysUserVo.getId());
        try {
            GroupDataVo groupDataVo = new GroupDataVo();
            List<String> stringList = new ArrayList<>();
            this.homeService.establishGroup(groupName,groupMember,sysUserVo.getId(),newGroupNumber);
            int index =0;
            //返回新群对象的头像
            for (Integer obj:groupMember) {
                if (index<4){
                    SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(obj);
                    stringList.add(sysIndividuality.getPortrait());
                }else {
                    break;
                }
                index++;
            }
            SysGroup sysGroup = this.homeService.selectGroupByGroupNumberVo(newGroupNumber);
            groupDataVo.setId(sysGroup.getId());
            groupDataVo.setMemberPortrait(stringList);
            groupDataVo.setGroupName(groupName);
            groupDataVo.setGroupText("[系统信息]");
            webSocketObj.setMsg("创建成功");
            webSocketObj.setState(3);
            webSocketObj.setData(groupDataVo);
            webSocketObj.setOk(true);
            String sendStr = objectMapper.writeValueAsString(webSocketObj);
            for (Integer obj:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(obj);
                for (Integer key:groupMember) {
                    if (obj==key){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }
        }catch (RuntimeException | IOException e){
            webSocketObj.setMsg("创建失败");
            webSocketObj.setState(3);
            webSocketObj.setOk(false);
            String sendStr = objectMapper.writeValueAsString(webSocketObj);
            for (Integer obj:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(obj);
                for (Integer key:groupMember) {
                    if (obj==key){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }
        }
    }
    private void sendText(String str) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer majorKeyId = jsonObject.getInt("majorKeyId");
        Integer stateCode = jsonObject.getInt("stateCode");
        Integer chatWay = jsonObject.getInt("chatWay");
        Integer sendId = jsonObject.getInt("sendId");
        //和好友聊天对方的Id
        Integer opposite = jsonObject.getInt("opposite");
        String chatContent = jsonObject.getString("chatContent");
        /*发送信息给好友*/
        if (stateCode==1){
            String text = "好友"+sysUserVo.getId();
            /*为好友添加提示信息并且把聊天窗口提到第一位,但是先查询对方是否正在和自己聊天*/
            SysUserVo user = this.homeService.selectUserById(opposite);
            /*查询我的个性装扮*/
            SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(sendId);
            SysUserVo myUser = this.homeService.selectUserById(sendId);
            /*根据好友关系表的主键添加好友聊天*/
            SysChat sysChat;
            /*查询出好友的关系表*/
            SysFriend sysFriend1 = this.homeService.selectFriendData(myUser.getId(),opposite);
            /*好友关系不存在*/
            if (sysFriend1==null){
                SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                sendDataAndUserData.setSendId(sysUserVo.getId());
                sendDataAndUserData.setChatContent("对方已经把你删除");
                sendDataAndUserData.setNoProblem(false);
                sendDataAndUserData.setState(4);
                String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sendId){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
                return;
            }
            /*判断是正在和当前好友聊天不进行添加提示信息*/
            if(user.getChildPage().equals(text)){
                sysChat = new SysChat();
                sysChat.setChatWay(chatWay.byteValue());
                sysChat.setChatContent(chatContent);
                sysChat.setSendId(sendId);
                sysChat.setRelationId(sysFriend1.getId());
                sysChat.setReceptionId(opposite);
                SendDataAndUserData sendDataAndUserData;
                try {
                    boolean isOk = this.homeService.insertChatByFriendNoHint(sysChat);
                    /*查询发送信息的用户头像,头像框,聊天气泡,用户名,发送信息的时间,信息的类型,信息的内容,等*/
                    sendDataAndUserData = new SendDataAndUserData();
                    sendDataAndUserData.setSendId(sysUserVo.getId());
                    sendDataAndUserData.setChatContent(sysChat.getChatContent());
                    sendDataAndUserData.setChatWay(sysChat.getChatWay());
                    sendDataAndUserData.setState(4);
                    sendDataAndUserData.setMajorKeyId(majorKeyId);
                    sendDataAndUserData.setOpposite(opposite);
                    sendDataAndUserData.setStateCode(stateCode);
                    sendDataAndUserData.setNickName(myUser.getNickName());
                    sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                    sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                    sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                    sendDataAndUserData.setSendDate(new Date());
                    sendDataAndUserData.setNoProblem(true);
                    String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                    for (Integer key:userMap.keySet()) {
                        HomeWebSocket homeWebSocket = userMap.get(key);
                        if (key==sysUserVo.getId()||key==opposite){
                            homeWebSocket.session.getBasicRemote().sendText(sendStr);
                        }
                    }
                }catch (RuntimeException e){
                    sendDataAndUserData = new SendDataAndUserData();
                    sendDataAndUserData.setSendId(sysUserVo.getId());
                    sendDataAndUserData.setChatContent("发送失败");
                    sendDataAndUserData.setState(4);
                    sendDataAndUserData.setNoProblem(false);
                    String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                    for (Integer key:userMap.keySet()) {
                        HomeWebSocket homeWebSocket = userMap.get(key);
                        if (key==sysUserVo.getId()){
                            homeWebSocket.session.getBasicRemote().sendText(sendStr);
                        }
                    }
                }
            }else {
                sysChat = new SysChat();
                sysChat.setChatWay(chatWay.byteValue());
                sysChat.setChatContent(chatContent);
                sysChat.setSendId(sendId);
                sysChat.setRelationId(sysFriend1.getId());
                sysChat.setReceptionId(opposite);
                try {
                    boolean isOk = this.homeService.insertChatByFriendYesHint(sysChat);
                    /*查询发送信息的用户头像,头像框,聊天气泡,用户名,发送信息的时间,信息的类型,信息的内容,等*/
                    SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                    sendDataAndUserData.setSendId(sysUserVo.getId());
                    sendDataAndUserData.setChatContent(sysChat.getChatContent());
                    sendDataAndUserData.setChatWay(sysChat.getChatWay());
                    sendDataAndUserData.setState(4);
                    sendDataAndUserData.setMajorKeyId(majorKeyId);
                    sendDataAndUserData.setOpposite(opposite);
                    sendDataAndUserData.setStateCode(stateCode);
                    sendDataAndUserData.setNickName(myUser.getNickName());
                    sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                    sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                    sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                    sendDataAndUserData.setNoProblem(true);
                    sendDataAndUserData.setSendDate(new Date());
                    String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                    for (Integer key:userMap.keySet()) {
                        HomeWebSocket homeWebSocket = userMap.get(key);
                        if (key==sysUserVo.getId()||key==opposite){
                            homeWebSocket.session.getBasicRemote().sendText(sendStr);
                        }
                    }
                }catch (RuntimeException e){
                    SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                    sendDataAndUserData.setSendId(sysUserVo.getId());
                    sendDataAndUserData.setChatContent("发送失败");
                    sendDataAndUserData.setChatWay(sysChat.getChatWay());
                    sendDataAndUserData.setState(4);
                    sendDataAndUserData.setMajorKeyId(opposite);
                    sendDataAndUserData.setStateCode(stateCode);
                    sendDataAndUserData.setNickName(myUser.getNickName());
                    sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                    sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                    sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                    sendDataAndUserData.setNoProblem(false);
                    String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                    for (Integer key:userMap.keySet()) {
                        HomeWebSocket homeWebSocket = userMap.get(key);
                        if (key==sysUserVo.getId()){
                            homeWebSocket.session.getBasicRemote().sendText(sendStr);
                        }
                    }
                }
            }
         }
        /*发送信息给群*/
        if (stateCode==2){
            String text = "群聊"+majorKeyId;
            SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(sendId);
            SysUserVo myUser = this.homeService.selectUserById(sendId);
            //查询该群的所有成员
            List<SysGroupMember> groupMember = this.homeService.selectGroupMemberByGroupId(majorKeyId);
            SysGroupChat sysGroupChat = new SysGroupChat();;
            sysGroupChat.setSendId(sendId);
            sysGroupChat.setGroupId(majorKeyId);
            sysGroupChat.setChatContent(chatContent);
            sysGroupChat.setChatWay(chatWay.byteValue());
            /*添加群提示信息*/
            for (SysGroupMember obj:groupMember) {
                SysUserVo user = this.homeService.selectUserById(obj.getUserId());
                /*查询出不在当前聊天页面的用户*/
                if (!user.getChildPage().equals(text)){
                    /*为他们添加提示信息*/
                    try {
                        this.homeService.insertChatByYesGroup(user.getId(),majorKeyId,(byte)2);
                    }catch (RuntimeException e){
                        SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                        sendDataAndUserData.setSendId(sysUserVo.getId());
                        sendDataAndUserData.setChatContent("发送失败");
                        sendDataAndUserData.setChatWay(sysGroupChat.getChatWay());
                        sendDataAndUserData.setState(4);
                        sendDataAndUserData.setMajorKeyId(majorKeyId);
                        sendDataAndUserData.setStateCode(stateCode);
                        sendDataAndUserData.setNickName(myUser.getNickName());
                        sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                        sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                        sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                        sendDataAndUserData.setNoProblem(false);
                        String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                        for (Integer key:userMap.keySet()) {
                            HomeWebSocket homeWebSocket = userMap.get(key);
                            if (key==sendId){
                                homeWebSocket.session.getBasicRemote().sendText(sendStr);
                            }
                        }
                        return;
                    }
                }
            }
            try{
                this.homeService.insertChatByNoGroup(sysGroupChat);
                SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                sendDataAndUserData.setSendId(sysUserVo.getId());
                sendDataAndUserData.setChatContent(sysGroupChat.getChatContent());
                sendDataAndUserData.setChatWay(sysGroupChat.getChatWay());
                sendDataAndUserData.setState(4);
                sendDataAndUserData.setMajorKeyId(majorKeyId);
                sendDataAndUserData.setStateCode(stateCode);
                sendDataAndUserData.setNickName(myUser.getNickName());
                sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                sendDataAndUserData.setNoProblem(true);
                sendDataAndUserData.setSendDate(new Date());
                String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    for (SysGroupMember obj:groupMember) {
                        if (key==obj.getUserId()){
                            homeWebSocket.session.getBasicRemote().sendText(sendStr);
                        }
                    }
                }
            }catch (RuntimeException e){
                SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                sendDataAndUserData.setSendId(sysUserVo.getId());
                sendDataAndUserData.setChatContent("发送失败");
                sendDataAndUserData.setChatWay(sysGroupChat.getChatWay());
                sendDataAndUserData.setState(4);
                sendDataAndUserData.setMajorKeyId(majorKeyId);
                sendDataAndUserData.setStateCode(stateCode);
                sendDataAndUserData.setNickName(myUser.getNickName());
                sendDataAndUserData.setChatBubble(sysIndividuality.getChatBubble());
                sendDataAndUserData.setPortrait(sysIndividuality.getPortrait());
                sendDataAndUserData.setPortraitFrame(sysIndividuality.getPortraitFrame());
                sendDataAndUserData.setNoProblem(false);
                String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sendId){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }
        }
    }
    private String newUserName(String groupNumber){
        boolean isExist = this.homeService.selectGroupByGroupNumber(groupNumber);
        if (isExist){
            return groupNumber;
        }else {
            Random random = new Random();
            String userNames = String.valueOf(random.nextInt(90000000) + 10000000);
            return newUserName(userNames);
        }
    }
    private void shareForFriend(String str) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer majorKeyId = jsonObject.getInt("majorKeyId");
        Integer shareFriendId = jsonObject.getInt("shareFriendId");
        Integer stateCode = jsonObject.getInt("stateCode");
        Integer chatWay = jsonObject.getInt("chatWay");
        Integer sendId = jsonObject.getInt("sendId");
        String text = "好友"+sysUserVo.getId();
        /*为好友添加提示信息并且把聊天窗口提到第一位,但是先查询对方是否正在和自己聊天*/
        SysUserVo user = this.homeService.selectUserById(majorKeyId);
        SysUserVo shareUser = this.homeService.selectUserById(shareFriendId);
        /*查询我的个性装扮*/
        SysIndividuality mySysIndividuality = this.homeService.selectUserIndividuality(sendId);
        SysIndividuality youSysIndividuality = this.homeService.selectUserIndividuality(shareFriendId);
        SysUserVo myUser = this.homeService.selectUserById(sendId);
        /*根据好友关系表的主键添加好友聊天*/
        SysChat sysChat;
        /*查询出好友的关系表*/
        SysFriend sysFriend = this.homeService.selectFriendData(myUser.getId(),majorKeyId);
        /*好友关系不存在*/
        if (sysFriend==null){
            SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
            sendDataAndUserData.setSendId(sysUserVo.getId());
            sendDataAndUserData.setChatContent("对方已经把你删除");
            sendDataAndUserData.setState(4);
            sendDataAndUserData.setNoProblem(false);
            String sendStr = objectMapper.writeValueAsString(sendDataAndUserData);
            for (Integer key:userMap.keySet()) {
                HomeWebSocket homeWebSocket = userMap.get(key);
                if (key==sendId){
                    homeWebSocket.session.getBasicRemote().sendText(sendStr);
                }
            }
            return;
        }
        if (user.getChildPage().equals(text)){
            sysChat = new SysChat();
            sysChat.setChatWay(chatWay.byteValue());
            sysChat.setChatContent(youSysIndividuality.getPortrait()+','+shareUser.getNickName()+','+shareUser.getUserName()+','+shareFriendId);
            sysChat.setSendId(sendId);
            sysChat.setRelationId(sysFriend.getId());
            sysChat.setReceptionId(majorKeyId);
            ShareFriendSend shareFriendSend;
            try {
                boolean isOk = this.homeService.insertChatByFriendNoHint(sysChat);
                shareFriendSend = new ShareFriendSend();
                shareFriendSend.setSendId(sendId);
                shareFriendSend.setMajorKeyId(sysFriend.getId());
                shareFriendSend.setState(5);
                shareFriendSend.setNickName(myUser.getNickName());
                shareFriendSend.setShareFriendName(user.getNickName());
                shareFriendSend.setChatBubble(mySysIndividuality.getChatBubble());
                shareFriendSend.setChatContent(sysChat.getChatContent());
                shareFriendSend.setChatWay(chatWay.byteValue());
                shareFriendSend.setNoProblem(true);
                shareFriendSend.setPortrait(mySysIndividuality.getPortrait());
                shareFriendSend.setPortraitFrame(mySysIndividuality.getPortraitFrame());
                shareFriendSend.setShareFriendName(shareUser.getNickName());
                shareFriendSend.setShareFriendPortrait(youSysIndividuality.getPortrait());
                shareFriendSend.setOpposite(majorKeyId);
                shareFriendSend.setShareFriendId(shareFriendId);
                shareFriendSend.setShareFriendUserName(shareUser.getUserName());
                shareFriendSend.setStateCode(stateCode);
                /*查询发送信息的用户头像,头像框,聊天气泡,用户名,发送信息的时间,信息的类型,信息的内容,等*/
                String sendStr = objectMapper.writeValueAsString(shareFriendSend);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sysUserVo.getId()||key==majorKeyId){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }catch (RuntimeException | JsonProcessingException e){
                shareFriendSend = new ShareFriendSend();
                shareFriendSend.setSendId(sendId);
                shareFriendSend.setChatContent("发送失败");
                shareFriendSend.setState(5);
                shareFriendSend.setNoProblem(false);
                shareFriendSend.setStateCode(stateCode);
                String sendStr = objectMapper.writeValueAsString(shareFriendSend);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sysUserVo.getId()){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }
        }else {
            sysChat = new SysChat();
            sysChat.setChatWay(chatWay.byteValue());
            sysChat.setChatContent(youSysIndividuality.getPortrait()+','+shareUser.getNickName()+','+shareUser.getUserName()+','+shareFriendId);
            sysChat.setSendId(sendId);
            sysChat.setRelationId(sysFriend.getId());
            sysChat.setReceptionId(majorKeyId);
            ShareFriendSend shareFriendSend;
            try {
                boolean isOk = this.homeService.insertChatByFriendYesHint(sysChat);
                shareFriendSend = new ShareFriendSend();
                shareFriendSend.setSendId(sendId);
                shareFriendSend.setMajorKeyId(sysFriend.getId());
                shareFriendSend.setState(5);
                shareFriendSend.setNickName(myUser.getNickName());
                shareFriendSend.setChatBubble(mySysIndividuality.getChatBubble());
                shareFriendSend.setChatContent(sysChat.getChatContent());
                shareFriendSend.setChatWay(chatWay.byteValue());
                shareFriendSend.setNoProblem(true);
                shareFriendSend.setPortrait(mySysIndividuality.getPortrait());
                shareFriendSend.setPortraitFrame(mySysIndividuality.getPortraitFrame());
                shareFriendSend.setShareFriendName(shareUser.getNickName());
                shareFriendSend.setShareFriendPortrait(youSysIndividuality.getPortrait());
                shareFriendSend.setOpposite(majorKeyId);
                shareFriendSend.setShareFriendId(shareFriendId);
                shareFriendSend.setShareFriendUserName(shareUser.getUserName());
                shareFriendSend.setStateCode(stateCode);
                /*查询发送信息的用户头像,头像框,聊天气泡,用户名,发送信息的时间,信息的类型,信息的内容,等*/
                String sendStr = objectMapper.writeValueAsString(shareFriendSend);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sysUserVo.getId()||key==majorKeyId){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }catch (RuntimeException | JsonProcessingException e){
                shareFriendSend = new ShareFriendSend();
                shareFriendSend.setSendId(sendId);
                shareFriendSend.setChatContent("发送失败");
                shareFriendSend.setState(5);
                shareFriendSend.setNoProblem(false);
                shareFriendSend.setStateCode(stateCode);
                String sendStr = objectMapper.writeValueAsString(shareFriendSend);
                for (Integer key:userMap.keySet()) {
                    HomeWebSocket homeWebSocket = userMap.get(key);
                    if (key==sysUserVo.getId()){
                        homeWebSocket.session.getBasicRemote().sendText(sendStr);
                    }
                }
            }
        }
    }
}
