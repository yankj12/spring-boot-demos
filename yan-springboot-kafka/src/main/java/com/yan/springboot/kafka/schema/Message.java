package com.yan.springboot.kafka.schema;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

}