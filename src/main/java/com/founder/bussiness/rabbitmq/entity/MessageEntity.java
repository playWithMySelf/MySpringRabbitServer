package com.founder.bussiness.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 5292613734328550245L;
    //发送人
    private String from;
    //接收人
    private String to;
    //发送内容
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
