package com.example.vuewithspring.DB;

import java.util.*;

public class NodeTree {
    ArrayList<Node> nodeArrayList = new ArrayList<>();
    Set<Node> nodeSet = new HashSet<>();
    Node root;

    int level;

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
            relateParentChild(rootNode,parentChildMap);
        }
    }

    public void relateParentChild(Node node,HashMap<String,HashMap<String,Node>> parentChildMap){
        for(String key: node.infoKeyMap.keySet()){
            if(parentChildMap.containsKey(key)){
                HashMap<String,Node> map = parentChildMap.get(key);
                node.children.putAll(map);
                for(String cKey:map.keySet()){
                    Node child = map.get(cKey);
                    child.parentNode=node;
                    child.rootNode=this.root;
                    relateParentChild(child,parentChildMap);
                }
            }
        }
    }

    public void viewTree(Node node){
        for(String key: node.infoKeyMap.keySet()){
            System.out.println(key);
            for(String cKey:node.children.keySet()){
                viewTree(node.children.get(cKey));
            }
        }
    }
}
