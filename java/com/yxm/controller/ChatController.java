package com.yxm.controller;

import com.yxm.po.SysGroup;
import com.yxm.po.SysUserVo;
import com.yxm.service.HomeService;
import com.yxm.util.JsonMsg;
import com.yxm.util.ProjectParameter;
import com.yxm.util.Tools;
import com.yxm.vo.ChatStateVo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private static final String UPLOAD_PATH="G:/yxm/javaProjectUp/BaseHotSpot/sendFile/";
    @Autowired
    private HomeService homeService;
    @RequestMapping("/chatHome")
    public ModelAndView chatHome(Integer majorKeyId,Integer state, HttpSession session){
        ModelAndView mv = new ModelAndView("/chat");
        SysUserVo sysUser = (SysUserVo) session.getAttribute(ProjectParameter.SESSION_USER);
        ChatStateVo chatStateVo;
        if (state==1){
            chatStateVo = new ChatStateVo();
            SysUserVo sysUserVo = this.homeService.selectUserById(majorKeyId);
            chatStateVo.setState(1);
            chatStateVo.setMajorKeyId(sysUserVo.getId());
            chatStateVo.setName(sysUserVo.getNickName());
            mv.addObject("user",chatStateVo);
            mv.addObject("myUser",sysUser);
        }
        if (state==2){
            //查询群聊信息和群聊人数信息
            chatStateVo = new ChatStateVo();
            SysGroup sysGroup = this.homeService.selectGroupById(majorKeyId);
            Integer count = this.homeService.selectGroupMemberCount(majorKeyId);
            chatStateVo.setState(2);
            chatStateVo.setMajorKeyId(sysGroup.getId());
            chatStateVo.setName(sysGroup.getGroupName()+"("+count+")");
            mv.addObject("user",chatStateVo);
            mv.addObject("myUser",sysUser);
        }
        return mv;
    }

    @RequestMapping(value = "/sendFile",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg sendFile(MultipartFile fileText) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();
        if(fileText==null){
            jsonMsg.setMsg("发送失败");
            return jsonMsg;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
        //判断文件存放目录是否存在
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        //拼接文件名  item.getName()--》文件名
        String fileName = dateFormat.format(new Date()) + System.nanoTime() + Tools.getFileExt(fileText.getOriginalFilename());
        //存放路径
        String filePaths = UPLOAD_PATH + fileName;
        File saveFile = new File(filePaths);
        //保存文件到硬盘
        fileText.transferTo(saveFile);
        jsonMsg.setMsg(fileName);
        jsonMsg.setState(true);
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
}
