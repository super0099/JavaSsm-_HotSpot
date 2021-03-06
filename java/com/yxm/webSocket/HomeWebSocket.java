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
            webSocketObj.setMsg("????????????????????????");
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
            webSocketObj.setMsg("????????????");
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
                webSocketObj.setMsg("????????????????????????");
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
            webSocketObj.setMsg("??????????????????");
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
        //????????????????????????Id
        String groupName = jsonObject.getString("groupName");
        WebSocketObj webSocketObj = new WebSocketObj();
        webSocketObj.setSendUserId(sysUserVo.getId());
        try {
            GroupDataVo groupDataVo = new GroupDataVo();
            List<String> stringList = new ArrayList<>();
            this.homeService.establishGroup(groupName,groupMember,sysUserVo.getId(),newGroupNumber);
            int index =0;
            //???????????????????????????
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
            groupDataVo.setGroupText("[????????????]");
            webSocketObj.setMsg("????????????");
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
            webSocketObj.setMsg("????????????");
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
        //????????????????????????Id
        Integer opposite = jsonObject.getInt("opposite");
        String chatContent = jsonObject.getString("chatContent");
        /*?????????????????????*/
        if (stateCode==1){
            String text = "??????"+sysUserVo.getId();
            /*???????????????????????????????????????????????????????????????,????????????????????????????????????????????????*/
            SysUserVo user = this.homeService.selectUserById(opposite);
            /*????????????????????????*/
            SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(sendId);
            SysUserVo myUser = this.homeService.selectUserById(sendId);
            /*????????????????????????????????????????????????*/
            SysChat sysChat;
            /*???????????????????????????*/
            SysFriend sysFriend1 = this.homeService.selectFriendData(myUser.getId(),opposite);
            /*?????????????????????*/
            if (sysFriend1==null){
                SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                sendDataAndUserData.setSendId(sysUserVo.getId());
                sendDataAndUserData.setChatContent("????????????????????????");
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
            /*???????????????????????????????????????????????????????????????*/
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
                    /*?????????????????????????????????,?????????,????????????,?????????,?????????????????????,???????????????,???????????????,???*/
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
                    sendDataAndUserData.setChatContent("????????????");
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
                    /*?????????????????????????????????,?????????,????????????,?????????,?????????????????????,???????????????,???????????????,???*/
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
                    sendDataAndUserData.setChatContent("????????????");
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
        /*??????????????????*/
        if (stateCode==2){
            String text = "??????"+majorKeyId;
            SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(sendId);
            SysUserVo myUser = this.homeService.selectUserById(sendId);
            //???????????????????????????
            List<SysGroupMember> groupMember = this.homeService.selectGroupMemberByGroupId(majorKeyId);
            SysGroupChat sysGroupChat = new SysGroupChat();;
            sysGroupChat.setSendId(sendId);
            sysGroupChat.setGroupId(majorKeyId);
            sysGroupChat.setChatContent(chatContent);
            sysGroupChat.setChatWay(chatWay.byteValue());
            /*?????????????????????*/
            for (SysGroupMember obj:groupMember) {
                SysUserVo user = this.homeService.selectUserById(obj.getUserId());
                /*??????????????????????????????????????????*/
                if (!user.getChildPage().equals(text)){
                    /*???????????????????????????*/
                    try {
                        this.homeService.insertChatByYesGroup(user.getId(),majorKeyId,(byte)2);
                    }catch (RuntimeException e){
                        SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
                        sendDataAndUserData.setSendId(sysUserVo.getId());
                        sendDataAndUserData.setChatContent("????????????");
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
                sendDataAndUserData.setChatContent("????????????");
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
        String text = "??????"+sysUserVo.getId();
        /*???????????????????????????????????????????????????????????????,????????????????????????????????????????????????*/
        SysUserVo user = this.homeService.selectUserById(majorKeyId);
        SysUserVo shareUser = this.homeService.selectUserById(shareFriendId);
        /*????????????????????????*/
        SysIndividuality mySysIndividuality = this.homeService.selectUserIndividuality(sendId);
        SysIndividuality youSysIndividuality = this.homeService.selectUserIndividuality(shareFriendId);
        SysUserVo myUser = this.homeService.selectUserById(sendId);
        /*????????????????????????????????????????????????*/
        SysChat sysChat;
        /*???????????????????????????*/
        SysFriend sysFriend = this.homeService.selectFriendData(myUser.getId(),majorKeyId);
        /*?????????????????????*/
        if (sysFriend==null){
            SendDataAndUserData sendDataAndUserData = new SendDataAndUserData();
            sendDataAndUserData.setSendId(sysUserVo.getId());
            sendDataAndUserData.setChatContent("????????????????????????");
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
                /*?????????????????????????????????,?????????,????????????,?????????,?????????????????????,???????????????,???????????????,???*/
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
                shareFriendSend.setChatContent("????????????");
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
                /*?????????????????????????????????,?????????,????????????,?????????,?????????????????????,???????????????,???????????????,???*/
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
                shareFriendSend.setChatContent("????????????");
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
