package com.example.vuewithspring.work;

import com.example.vuewithspring.DB.JsonConvert;
import com.example.vuewithspring.DB.Node;
import com.example.vuewithspring.DB.NodeTree;
import com.example.vuewithspring.DB.info;
import org.apache.commons.io.IOUtils;
import org.apache.jena.atlas.json.JsonObject;
import org.json.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class doI {
    JsonConvert jsonConvert = new JsonConvert();

    public String getJsonData() {

        StringBuffer result = new StringBuffer();
        try {

            String fileLocation = "D:/TestProject/VueWithSpring/src/main/resources/assets/XML/ben.xml";
            File file = new File(fileLocation);


//			불러온 XML 파일을 DOM으로 읽기 위한 사전작업
            if(file.isFile()){
                InputStream inputStream = new FileInputStream(file);
                String xml = IOUtils.toString(inputStream);
                JSONObject jsonObject = XML.toJSONObject(xml);
//                JSONObject jmap = (JSONObject) jsonObject.get("ben");
                HashMap<String,HashMap>  hashMap = (HashMap) jsonObject.toMap();
                HashMap<String,ArrayList> people = hashMap.get("Ben");
                NodeTree nodeTree = new NodeTree();
                for(String pKey:people.keySet()){
                    ArrayList<HashMap> list = people.get(pKey);
                    for(HashMap map:list){
                        info data = new info(map);
                        Node node = new Node(data.getInfoToNode());
                        nodeTree.setNodeArrayList(node);
                    }
                }
                nodeTree.setTreeStructure();
                nodeTree.viewTree(nodeTree.getRoot());


            }else{
                throw new Exception("파일 없음");
            }


        }catch (Exception e){

            e.printStackTrace();

        }


        return result.toString();
    }
}
