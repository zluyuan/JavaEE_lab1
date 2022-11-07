package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("node")
@Data
public class Node {
    @TableId
    private String nodeId;
    private String nodeName;
    private String devEui;
}