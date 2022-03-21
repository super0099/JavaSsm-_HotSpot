package com.yxm.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxm.common.GetUserSession;
import com.yxm.po.SysUserVo;
import com.yxm.service.HomeService;
import com.yxm.util.ProjectParameter;
import net.sf.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatWebSocket",configurator = GetUserSession.class)
public class ChatWebSocket {
    private static final Map<Integer,ChatWebSocket> userMap = new ConcurrentHashMap<>();
    private HttpSession httpSession;
    private SysUserVo sysUserVo;
    private Session session;
    private ObjectMapper objectMapper = new ObjectMapper();
    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

    private HomeService homeService = (HomeService) webApplicationContext.getBean("homeServiceImpl");
    @OnOpen
    public void OnOpen(Session session, EndpointConfig config) throws IOException {
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.sysUserVo = (SysUserVo) httpSession.getAttribute(ProjectParameter.SESSION_USER);
        this.session =session;
        userMap.put(sysUserVo.getId(),this);
    }
    @OnMessage
    public void OnMessage(String str) throws IOException {
        JSONObject jsonObject =JSONObject.fromObject(str);
        Integer state = jsonObject.getInt("state");
        if (state==1){

        }
        if(state==2){
        }
        if (state==3){
        }
    }
    @OnError
    public void OnError(Throwable throwable){

    }
    @OnClose
    public void OnClose(){
        userMap.remove(sysUserVo.getId());
    }
}
