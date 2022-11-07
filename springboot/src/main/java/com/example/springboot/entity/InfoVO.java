package com.example.springboot.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.mapper.GatewayMapper;
import com.example.springboot.mapper.NodeMapper;
import lombok.Data;

@Data
public class InfoVO extends Info{
    private String nodeName;
    private String gatewayName;
    private Double longitude;
    private Double latitude;
    private String devEui;
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public void setGatewayName(String gatewayName) { this.gatewayName = gatewayName; }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setDevEui(String devEui) { this.devEui= devEui; }

    public InfoVO(Info info) {
        setInfoId(info.getInfoId());
        setNodeId(info.getNodeId());
        setGatewayId(info.getGatewayId());
        setArrivalTime(info.getArrivalTime());
        setPriority(info.getPriority());
    }

    public InfoVO(String infoId, String nodeName, String devEui, String gatewayName, Double longitude, Double latitude, String arrivalTime, Integer priority) {
        setInfoId(infoId);
        setNodeName(nodeName);
        setDevEui(devEui);
        setGatewayName(gatewayName);
        setLongitude(longitude);
        setLatitude(latitude);
        setArrivalTime(arrivalTime);
        setPriority(priority);
    }

    public void addNode(NodeMapper nodeMapper) {
        LambdaQueryWrapper<Node> wrapper = Wrappers.lambdaQuery(Node.class).eq(Node::getNodeId, this.getNodeId());
        Node node = nodeMapper.selectOne(wrapper);
        setNodeName(node.getNodeName());
        setDevEui(node.getDevEui());
    }
    public void addGateway(GatewayMapper gatewayMapper){
        LambdaQueryWrapper<Gateway> wrapper = Wrappers.lambdaQuery(Gateway.class).eq(Gateway::getGatewayId, this.getGatewayId());
        Gateway gateway = gatewayMapper.selectOne(wrapper);
        setGatewayName(gateway.getGatewayName());
        setLongitude(gateway.getLongitude());
        setLatitude(gateway.getLatitude());
    }
}