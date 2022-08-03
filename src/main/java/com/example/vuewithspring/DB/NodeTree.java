package com.example.vuewithspring.DB;

import lombok.Getter;

import java.util.*;

@Getter
public class NodeTree {
    ArrayList<Node> nodeArrayList = new ArrayList<>();
    Set<Node> nodeSet = new HashSet<>();
    Node root;

    int depth=0;

    public void setTree(Node node){
        this.root=node;
    }

    public void setNodeArrayList(Node node) {
        if(nodeSet.add(node)){
            nodeArrayList.add(node);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setTreeStructure(){
        HashMap<String,Node> nodeMap = new HashMap<>();
        HashMap<String,HashMap<String,Node>> parentChildMap = new HashMap<>();

        ArrayList<Node> rootList = new ArrayList<>();
        for(Node node : nodeArrayList){
            for(String key : node.infoKeyMap.keySet()){
                info data = node.infoKeyMap.get(key);
                HashMap dataMap = data.infoMap;
                if(dataMap.containsKey("father")){
                    String parent = (String) dataMap.get("father");
                    if(parent==null){
                        this.root=node;
                        node.isRoot=true;
                    }else{
                        HashMap temp;
                        if(parentChildMap.containsKey(parent)){
                            temp = parentChildMap.get(parent);
                            temp.put(key,node);
                        }else{
                            temp = new HashMap<>();
                            temp.put(key,node);
                            parentChildMap.put(parent,temp);
                        }
                        nodeMap.put(key,node);
                    }
                }else{
                    continue;
                }
            }
        }
        if(this.root!=null){
            Node rootNode = this.root;
            this.root.setLevel(0);
            this.depth=this.root.level;
            relateParentChild(rootNode,parentChildMap);
        }
    }

    public void relateParentChild(Node node,HashMap<String,HashMap<String,Node>> parentChildMap){
        if(this.depth<node.level){
            this.depth=node.level;
        }
        for(String key: node.infoKeyMap.keySet()){
            if(parentChildMap.containsKey(key)){
                HashMap<String,Node> map = parentChildMap.get(key);
                node.children.putAll(map);
                node.width=map.size();
                for(String cKey:map.keySet()){
                    Node child = map.get(cKey);
                    child.parentNode=node;
                    child.setLevel(node.level+1);
                    child.rootNode=this.root;
                    relateParentChild(child,parentChildMap);
                }
            }else if(node.children.size()==0){
                node.isLeaf=true;
            }
        }
    }



}
