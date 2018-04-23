package com.founder.bussiness.rabbitmq.controller;

import com.founder.bussiness.Base.BaseAction;
import com.founder.bussiness.rabbitmq.entity.MessageEntity;
import net.sf.json.JSONObject;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@Controller
@Scope("prototype")
@RequestMapping("/Amqp")
public class AmqpController extends BaseAction {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //发送topicMsg消息
    @RequestMapping(params = "method=sendtopicMsg")
    public ModelAndView sendtopicMsg(HttpServletRequest req, HttpServletResponse res) {
        //页面参数
        Map param  = this.getParameterMap2(req);
        JSONObject msg;
        msg = JSONObject.fromObject(param.get("msg"));

        MessageEntity met = (MessageEntity)JSONObject.toBean(msg, MessageEntity.class);
        Map dataMap = new HashMap();
        try{
            amqpTemplate.convertAndSend("RabbitServer.topic.exchange", "topic.message.user1", new Message(SerializationUtils.serialize(met), new MessageProperties()));
            dataMap.put("code","EC00");
            dataMap.put("msg","发送成功");
        }catch (Exception e){
            e.printStackTrace();
            // 写回客户端
            dataMap.put("code","EC01");
            dataMap.put("msg","发送失败");
            this.WriteJsonObjToPage(dataMap, res);
            // 返回
            return null;
        }

        // 写回客户端
        this.WriteJsonObjToPage(dataMap, res);
        // 返回
        return null;
    }

    //发送direct Msg消息
    @RequestMapping(params = "method=sendDirectMsg")
    public ModelAndView sendDirectMsg(HttpServletRequest req, HttpServletResponse res) {
        //页面参数
        Map param  = this.getParameterMap2(req);
        JSONObject msg;
        msg = JSONObject.fromObject(param.get("msg"));

        MessageEntity met = (MessageEntity)JSONObject.toBean(msg, MessageEntity.class);
        Map dataMap = new HashMap();
        try{
            amqpTemplate.convertAndSend("RabbitServer.direct.exchange", "directQueue", new Message(SerializationUtils.serialize(met), new MessageProperties()));
            dataMap.put("code","EC00");
            dataMap.put("msg","发送成功");
        }catch (Exception e){
            e.printStackTrace();
            // 写回客户端
            dataMap.put("code","EC01");
            dataMap.put("msg","发送失败");
            this.WriteJsonObjToPage(dataMap, res);
            // 返回
            return null;
        }

        // 写回客户端
        this.WriteJsonObjToPage(dataMap, res);
        // 返回
        return null;
    }
}
