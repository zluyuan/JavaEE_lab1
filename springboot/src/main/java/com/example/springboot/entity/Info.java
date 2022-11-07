package com.example.springboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("info")
@Data
public class Info {
    @TableId(type= IdType.AUTO)
    private String infoId;
    private String nodeId;
    private String gatewayId;
    private String arrivalTime;
    private Integer priority;
}
