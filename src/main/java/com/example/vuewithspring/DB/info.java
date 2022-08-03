package com.example.vuewithspring.DB;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Getter
public class info {
    boolean status;
    boolean exist;
    String key;
    HashMap infoMap = new HashMap();

    public info(HashMap infoMap) {
        this.infoMap = infoMap;
        isUpdate();
        if(this.status){
            setExist(true);
            isUpdate();
        }
    }

    void setAttribute(String key, Object value){
        if(!exist){
            infoMap.put(key,value);
            isUpdate();
        }
    }
    void isUpdate(){
        this.status=infoMap.containsKey("name")&&infoMap.containsKey("type")&&infoMap.containsKey("version");
        if(status&&exist){
            key=infoMap.get("name").toString()+'-'+infoMap.get("type").toString()+'-'+(int)infoMap.get("version");
        }
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public info mergeInfo(info other){
        this.infoMap.putAll(other.infoMap);
        return this;
    }


    public HashMap getInfoToNode() {
        HashMap nodeMap = new HashMap();
        nodeMap.put(this.key,this);
        return nodeMap;
    }
}
