package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("gateway")
@Data
public class Gateway {
    @TableId
    private String gatewayId;
    private String gatewayName;
    private Double longitude;
    private Double latitude;
}
