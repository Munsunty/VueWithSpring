package com.example.vuewithspring.DB;

import org.json.JSONObject;

import java.util.HashMap;

public class Node {
    Node parentNode;
    Node rootNode;
    boolean isRoot;
    boolean isLeaf;

    HashMap<String, Node> children=new HashMap<String, Node>();
    HashMap<String,info> infoKeyMap = new HashMap();


    public Node() {
    }
    public Node(HashMap<String, info> infoKeyMap) {
        this.infoKeyMap = infoKeyMap;
    }

    public void setNode(String key, info nf){
        if(infoKeyMap.containsKey(key)){
            info item = infoKeyMap.get(key);
            item.mergeInfo(nf);
        }else{
            infoKeyMap.put(key,nf);
        }
    }

    public String getJson(){
        StringBuffer sb = new StringBuffer();
        for(String key : this.infoKeyMap.keySet()){
            sb.append("{\"data:\":").append("{");
            sb.append("\"id\":");
            sb.append("\"").append(key).append("\",");
            info data = this.infoKeyMap.get(key);
            JSONObject dataObject = new JSONObject(data.infoMap);
            sb.append("\"info\":");
            sb.append(dataObject.toString());
            sb.append("}},");
        }
        if(children.size()>0){
            for(String key : children.keySet()){
                Node node = children.get(key);
                for(String pKey:this.infoKeyMap.keySet()){
                    sb.append("{\"data:\":").append("{");
                    sb.append("\"id\":");
                    sb.append("\"").append(pKey+"--"+key).append("\",");
                    sb.append("\"source\":");
                    sb.append("\"").append(pKey).append("\",");
                    sb.append("\"target\":");
                    sb.append("\"").append(key).append("\"");
                    sb.append("}},");
                }
                sb.append(node.getJson()+",");
            }
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }

}
