package com.example.springboot.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.common.Result;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signal")
public class SignalController {
    @Resource
    GatewayMapper gatewayMapper;
    @Resource
    NodeMapper nodeMapper;
    @Resource
    InfoMapper infoMapper;

    @GetMapping
    public Result<?> get() {
        QueryWrapper<Node> nodeWrapper=new QueryWrapper<Node>();
        List<Node> nodes=nodeMapper.selectList(nodeWrapper);
        QueryWrapper<Info> infoWrapper=new QueryWrapper<Info>();
        List<Info> infos=infoMapper.selectList(infoWrapper);
        List<Info> infos1=new ArrayList<Info>();
        List<String> nodeIds=nodes.stream().map(Node::getNodeId).collect(Collectors.toList());
        HashMap<String,Integer> hashMap=new HashMap<>();
        List<InfoVO> infosVO=new ArrayList<InfoVO>();
        for(String id: nodeIds){
            hashMap.put(id,0);
        }
        for(Info info: infos){
            if(hashMap.get(info.getNodeId())==info.getPriority()){
                infos1.add(info);
            }
            else if(hashMap.get(info.getNodeId())<info.getPriority()){
                hashMap.put(info.getNodeId(),info.getPriority());
                if(infos1.size()!=0) {
                    Iterator<Info> it= infos1.iterator();
                    while(it.hasNext()){
                        Info in=it.next();
                        if(in.getNodeId().equals(info.getNodeId())){
                            it.remove();
                        }
                    }
                }
                infos1.add(info);
            }
        }
        for(Info info:infos1){
            InfoVO infoVO=new InfoVO(info);
            addNodeInfo(infoVO);
            addGatewayInfo(infoVO);
            infosVO.add(infoVO);
        }
        return Result.success(infosVO);
    }

    private void addNodeInfo(InfoVO infoVO) {
        Node node = nodeMapper.selectOne(Wrappers.lambdaQuery(Node.class).eq(Node::getNodeId, infoVO.getNodeId()));
        infoVO.setNodeName(node.getNodeName());
        infoVO.setDevEui(node.getDevEui());
    }
    private void addGatewayInfo(InfoVO infoVO) {
        Gateway gateway = gatewayMapper.selectOne(Wrappers.lambdaQuery(Gateway.class).eq(Gateway::getGatewayId, infoVO.getGatewayId()));
        infoVO.setGatewayName(gateway.getGatewayName());
        infoVO.setLongitude(gateway.getLongitude());
        infoVO.setLatitude(gateway.getLatitude());
    }

}