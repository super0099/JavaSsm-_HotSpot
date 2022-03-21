package com.yxm.controller;
import com.yxm.po.*;
import com.yxm.service.HomeService;
import com.yxm.util.JsonMsg;
import com.yxm.util.ProjectParameter;
import com.yxm.util.Tools;
import com.yxm.vo.*;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import net.sourceforge.pinyin4j.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/home");
        HttpSession session = request.getSession();
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        if(sysUserVo!=null){
            SysIndividuality sysIndividuality = this.homeService.selectUserIndividuality(sysUserVo.getId());
            mv.addObject("sysUserVo",sysUserVo);
            mv.addObject("sysIndividuality",sysIndividuality);
            return mv;
        }else {
            mv = new ModelAndView("redirect:/");
            return mv;
        }
    }
    @RequestMapping("/onePage")
    public String onePage(){
        return "/onePage";
    }
    /**
     * 首次加载聊天页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/friendChatLoad",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadChatMessageVo> friendChatLoad(HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        List<LoadChatMessageVo> loadChatMessageVos = new ArrayList<>();
        LoadChatMessageVo loadChatMessageVo;
        //好友聊天
        List<UserIndividualityVo> UserIndividualityAndChat = this.homeService.selectUserChatAndFriend(sysUserVo.getId());
        List<SysHint> selectFriendHint = this.homeService.selectFriendHint(sysUserVo.getId());

        for (UserIndividualityVo obj:UserIndividualityAndChat) {
            UserIndividualityVo vo = this.homeService.selectChat(obj.getRelationId());
            loadChatMessageVo = new LoadChatMessageVo();
            loadChatMessageVo.setMessageType(1);
            loadChatMessageVo.setSendDate(vo.getSendDate());
            loadChatMessageVo.setNickName(obj.getNickName());
            loadChatMessageVo.setPortrait(obj.getPortrait());
            loadChatMessageVo.setPortraitFrame(obj.getPortraitFrame());
            loadChatMessageVo.setMajorKeyId(obj.getUserId());
            loadChatMessageVo.setChatWay(vo.getChatWay());
            if (vo.getChatWay()==2){
                loadChatMessageVo.setChatText("[图片]");
            }
            if(vo.getChatWay()==1){
                loadChatMessageVo.setChatText(vo.getChatContent());
            }
            if (vo.getChatWay()==3){
                loadChatMessageVo.setChatText("[名片分享]");
            }
            if (vo.getChatWay()==4){
                loadChatMessageVo.setChatText("[系统提示]");
            }
            for (SysHint hintObj:selectFriendHint) {
                if(obj.getUserId()==hintObj.getInitiativeId()){
                    loadChatMessageVo.setMessageCount(hintObj.getMessageCount());
                }
            }
            loadChatMessageVos.add(loadChatMessageVo);
        }
        //群聊
        List<GroupChatLoadVo> groupLoad = this.homeService.groupChatLoad(sysUserVo.getId());
        for (GroupChatLoadVo obj:groupLoad) {
            GroupChatLoadVo groupChatLoadVo = this.homeService.selectGroupChatLast(obj.getGroupId());
            loadChatMessageVo = new LoadChatMessageVo();
            loadChatMessageVo.setChatWay(groupChatLoadVo.getChatWay());
            if(groupChatLoadVo.getChatWay()==1){
                loadChatMessageVo.setChatText(groupChatLoadVo.getChatContent());
            }
            if (groupChatLoadVo.getChatWay()==2){
                loadChatMessageVo.setChatText("[图片]");
            }
            if (groupChatLoadVo.getChatWay()==4){
                loadChatMessageVo.setChatText("[系统提示]");
            }
            loadChatMessageVo.setMessageType(2);
            loadChatMessageVo.setMajorKeyId(obj.getGroupId());
            loadChatMessageVo.setNickName(obj.getGroupName());
            loadChatMessageVo.setSendDate(groupChatLoadVo.getSendDate());
            SysHint sysHint = this.homeService.selectGroupHint(sysUserVo.getId(), (byte) 2,obj.getGroupId());
            //用户头像
            List<SysIndividuality> sysIndividuality = this.homeService.selectGroupMemberIndividuality(obj.getGroupId());
            List<String> portrait = new ArrayList<>();
            for (SysIndividuality data:sysIndividuality) {
                portrait.add(data.getPortrait());
            }
            if (sysHint!=null){
                loadChatMessageVo.setMessageCount(sysHint.getMessageCount());
            }
            loadChatMessageVo.setMemberPortrait(portrait);
            loadChatMessageVos.add(loadChatMessageVo);
        }
        //排序
        loadChatMessageVos.sort(Comparator.comparing(LoadChatMessageVo::getSendDate));
        Collections.reverse(loadChatMessageVos);
        return loadChatMessageVos;
    }

    /**
     *返回通讯录
     * @return
     */
    @RequestMapping(value = "/friendLoad",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<FriendRelation> friendLoad(HttpServletRequest request) throws BadHanyuPinyinOutputFormatCombination {
        HttpSession session = request.getSession();
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        List<FriendRelation> selectFriendLoad = this.homeService.selectFriendLoad(sysUserVo.getId());
        for (FriendRelation obj:selectFriendLoad) {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            //拼音小写
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            //不带声调
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            //要转换的中文，格式，转换之后的拼音的分隔符，遇到不能转换的是否保留
            obj.setSpell(PinyinHelper.toHanYuPinyinString(obj.getNickName(), format, "", true));
        }
        return selectFriendLoad;
    }

    /**
     * 查询好友
     * @param friendNumber
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryFriend",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg queryFriend(String friendNumber,HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        JsonMsg jsonMsg = new JsonMsg();
        if (Tools.isNotNull(friendNumber)){
            if(!sysUserVo.getUserName().equals(friendNumber)){
                FriendRelation friendRelation = this.homeService.selectFriend(friendNumber);
                if(friendRelation!=null){
                    FriendRelation friendRelationIsOk = this.homeService.selectFriendIsOk(sysUserVo.getId(),friendRelation.getUserId());
                    if (friendRelationIsOk==null||friendRelationIsOk.getState()==3){
                        jsonMsg.setState(true);
                        jsonMsg.setData(friendRelation);
                    }else if(friendRelationIsOk.getState()==1){
                        jsonMsg.setMsg(friendRelationIsOk.getNickName()+"用户已经是你的好友");
                    }
                }else {
                    jsonMsg.setMsg("该用户不存在");
                }
            }else {
                jsonMsg.setMsg("无效的搜索");
            }
        }else {
            jsonMsg.setMsg("请输入账号");
        }
        return jsonMsg;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/newFriendHint",produces = "application/json;charset=utf-8")
    @ResponseBody
    public SysHint newFriendHint(HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        SysHint sysHint = this.homeService.newFriendHint(sysUserVo.getId());
        return sysHint;
    }

    /**
     *同意好友信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/examineFriend",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<FriendRelation> examineFriend(HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        SysHint sysHint = new SysHint();
        sysHint.setHintType((byte) 3);
        sysHint.setUserId(sysUserVo.getId());
        sysHint.setMessageCount(0);
        List<FriendRelation> friendRelationIsOk = new ArrayList<>();
        //先将提示信息清0
        boolean isOk = this.homeService.updateMyHint(sysHint);
        if (isOk){
            friendRelationIsOk = this.homeService.selectFriendHintFalse(sysUserVo.getId());
        }
        return friendRelationIsOk;
    }

    /**
     * 查询添加群下方的好友
     * @param session
     * @return
     */
    @RequestMapping(value = "/selectGroupFriend",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<FriendRelation> selectGroupFriend(HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        return this.homeService.selectFriendLoad(sysUserVo.getId());
    }
    /*打开好友进行聊天改变自己的聊天状态*/
    @RequestMapping(value = "/alterState",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg alterState(Integer majorKeyId,Integer state,HttpSession session){
        SysUserVo user = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        SysUserVo sysUserVo = new SysUserVo();
        JsonMsg jsonMsg = new JsonMsg();
        String text = null;
        ChatStateVo chatStateVo = new ChatStateVo();
        if (state==1){
            text="好友";
            SysUserVo UserVo = this.homeService.selectUserById(majorKeyId);
            SysFriend sysFriend = this.homeService.selectFriendData(user.getId(),majorKeyId);
            chatStateVo.setState(1);
            chatStateVo.setMajorKeyId(sysFriend.getId());
            chatStateVo.setName(UserVo.getNickName());
        }
        if (state==2){
            text="群聊";
            SysGroup sysGroup = this.homeService.selectGroupById(majorKeyId);
            Integer count = this.homeService.selectGroupMemberCount(majorKeyId);
            chatStateVo.setState(2);
            chatStateVo.setMajorKeyId(sysGroup.getId());
            chatStateVo.setName(sysGroup.getGroupName()+"("+count+")");
        }
        /*修改提示数量*/
        sysUserVo.setId(user.getId());
        sysUserVo.setChildPage(text+majorKeyId);
        boolean isOk = this.homeService.updateUserPageState(sysUserVo,majorKeyId,state);
        if (isOk){
            jsonMsg.setState(true);
            jsonMsg.setData(chatStateVo);
        }
        return jsonMsg;
    }

    /**
     *回填好友聊天信息
     * @return
     */
    @RequestMapping(value = "/backFill",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<FriendAndFriendChat> backFill(Integer majorKeyId,Integer page,HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        SysFriend sysFriend = this.homeService.selectFriendData(sysUserVo.getId(),majorKeyId);
        List<FriendAndFriendChat> sysChats = this.homeService.selectFriendChatById(sysFriend.getId(),page);
        if (page>0){
            return sysChats;
        }else {
            Collections.reverse(sysChats);
            return sysChats;
        }
    }

    /**
     * 回填群聊聊天信息
     */
    @RequestMapping(value = "/backGroupFill",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<FriendAndFriendChat> backGroupFill(Integer majorKeyId,Integer page){
        List<FriendAndFriendChat> sysChats = this.homeService.selectGroupChatById(majorKeyId,page);
        if (page>0){
            return sysChats;
        }else {
            Collections.reverse(sysChats);
            return sysChats;
        }
    }
    /**
     * 右键查看通讯录好友信息
     */
    @RequestMapping(value = "/friendData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public FriendDataVo selectFriendData(Integer majorKeyId){
        FriendDataVo friendDataVo = this.homeService.selectFriendBase(majorKeyId);
        return friendDataVo;
    }

    @RequestMapping(value = "/openBusinessCard",produces = "application/json;charset=utf-8")
    @ResponseBody
    public FriendDataVo openBusinessCard(Integer majorKeyId,HttpSession session){
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        JsonMsg jsonMsg = new JsonMsg();
        SysFriend sysFriend = this.homeService.selectBusinessCard(sysUserVo.getId(),majorKeyId);
        FriendDataVo friendDataVo = this.homeService.selectFriendBase(majorKeyId);;
        if (sysFriend==null){
            friendDataVo.setOK(false);
        }else {
            friendDataVo.setOK(true);
        }
        return friendDataVo;
    }

    @RequestMapping(value = "/deleteFriend",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg deleteFriend(Integer majorKeyId,HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        SysFriend sysFriend = this.homeService.selectFriendData(majorKeyId,sysUserVo.getId());
        if (sysFriend==null){
            jsonMsg.setMsg("对方已经不是你的好友");
            return jsonMsg;
        }
        boolean isOK = this.homeService.deleteFriend(sysFriend);
        if (isOK){
            jsonMsg.setMsg("删除成功");
            jsonMsg.setState(true);
        }else {
            jsonMsg.setMsg("删除失败");
        }
        return jsonMsg;
    }
}
