package com.yxm.controller;

import com.yxm.po.SysIndividuality;
import com.yxm.po.SysUserVo;
import com.yxm.service.LoginService;
import com.yxm.util.JsonMsg;
import com.yxm.util.MD5Util;
import com.yxm.util.ProjectParameter;
import com.yxm.util.Tools;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Random;


@Controller
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private LoginService loginService;

    private static final String UPLOAD_PATH="G:/yxm/javaProjectUp/BaseHotSpot/user/";


    @RequestMapping("/index")
    public String index(){
        return "/login";
    }

    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg login(String userName, String password, HttpServletRequest request){
        JsonMsg jsonMsg = new JsonMsg();
        HttpSession session = request.getSession();
        if(userName!=null){
            if(password!=null){
                SysUserVo sysUserVo = this.loginService.selectUserName(userName);
                if (sysUserVo!=null){
                    if(sysUserVo.getLoginCount()>0){
                        String md = MD5Util.getMD5(password+sysUserVo.getSalt());
                        if (md.equals(sysUserVo.getUserPassword())){
                            session.setAttribute(ProjectParameter.SESSION_USER,sysUserVo);
                            jsonMsg.setState(true);
                            jsonMsg.setData(sysUserVo);
                        }else {
                            //改变剩余的登录次数
                            Integer count = sysUserVo.getLoginCount();
                            try{
                                boolean isOk = this.loginService.updateUserState(sysUserVo.getId(),count-1);
                                if (isOk){
                                    jsonMsg.setMsg("密码错误,剩余登录次数"+(count-1));
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }else {
                        jsonMsg.setMsg("该用户存在风险暂时不能登录");
                    }
                }else {
                    jsonMsg.setMsg("该用户不存在");
                }
            }else {
                jsonMsg.setMsg("密码不能为空");
            }
        }else {
            jsonMsg.setMsg("账号不能为空");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg register(String onePassword,String twoPassword,String phone,String idNumber){
        JsonMsg jsonMsg = new JsonMsg();
        if (Tools.isNotNull(onePassword)){
            if (Tools.isNotNull(twoPassword)){
                if (onePassword.equals(twoPassword)){
                    if (Tools.isNotNull(phone)){
                        if (Tools.isNotNull(idNumber)){
                            SysUserVo sysUserVo = new SysUserVo();
                            sysUserVo.setState((byte) 1);
                            sysUserVo.setGender((byte)1);
                            sysUserVo.setLoginCount(5);
                            sysUserVo.setUserIdNumber(idNumber);
                            Random random = new Random();
                            //生成一个随机的8位数作为盐   10000000 ~ 99999999
                            String salt = String.valueOf(random.nextInt(90000000) + 10000000);
                            //对输入的密码+盐 取MD5值
                            sysUserVo.setSalt(salt);
                            String userPassword = MD5Util.getMD5(onePassword + salt);
                            sysUserVo.setUserPassword(userPassword);
                            String userName = String.valueOf(random.nextInt(90000000) + 10000000);
                            //查询注册的账号是否存在
                            String newUser = newUserName(userName);
                            sysUserVo.setUserName(newUser);
                            sysUserVo.setUserPhone(phone);
                            sysUserVo.setNickName(userName);
                            try{
                                boolean isOk = this.loginService.insertUser(sysUserVo);
                                if(isOk){
                                    jsonMsg.setMsg("注册成功");
                                    jsonMsg.setState(true);
                                    jsonMsg.setData(sysUserVo);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else {
                            jsonMsg.setMsg("身份证号不能为空");
                        }
                    }else {
                        jsonMsg.setMsg("手机号不能为空");
                    }
                }else {
                    jsonMsg.setMsg("两次输入的密码不一致");
                }
            }else {
                jsonMsg.setMsg("确认密码不能为空");
            }
        }else {
            jsonMsg.setMsg("密码不能为空");
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/onblur",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg onblur(String userName){
        JsonMsg jsonMsg = new JsonMsg();
        SysIndividuality sysIndividuality = this.loginService.selectUserPortrait(userName);
        if (sysIndividuality!=null){
            jsonMsg.setState(true);
            jsonMsg.setData(sysIndividuality);
        }else {
            jsonMsg.setState(false);
        }
        return jsonMsg;
    }
    /**
     * 根据图片名返回图片 流
     */
    @RequestMapping(value = "/getPortraitImage")
    public void getPortraitImage(String imgName, HttpServletResponse response) throws IOException {
        if (Tools.isNotNull(imgName)){
            //图片名不未空
            String imgPath=UPLOAD_PATH+imgName;
            File fileImg=new File(imgPath);
            if (fileImg.exists()){
                //指定返的类型
                response.setContentType(Tools.getImageContentType(imgName));

                InputStream in=null;
                OutputStream out=null;
                try {
                    in= new FileInputStream(fileImg);
                    out=response.getOutputStream();
                    IOUtils.copy(in,out);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in!=null)in.close();
                    if (out!=null)out.close();
                }
            }
        }
    }

    private String newUserName(String userName){
        boolean isExist = this.loginService.selectUserNameExist(userName);
        if (isExist){
            return userName;
        }else {
            Random random = new Random();
            String userNames = String.valueOf(random.nextInt(90000000) + 10000000);
            return newUserName(userNames);
        }
    }
}
