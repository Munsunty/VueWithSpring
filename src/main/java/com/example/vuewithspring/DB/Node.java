package com.example.vuewithspring.DB;

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

}
