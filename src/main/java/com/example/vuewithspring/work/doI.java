package com.example.vuewithspring.work;

import com.example.vuewithspring.DB.JsonConvert;
import com.example.vuewithspring.DB.Node;
import com.example.vuewithspring.DB.NodeTree;
import com.example.vuewithspring.DB.info;
import org.apache.commons.io.IOUtils;
import org.apache.jena.atlas.json.JSON;
import org.apache.jena.atlas.json.JsonObject;
import org.json.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class doI {
    JsonConvert jsonConvert = new JsonConvert();

    static HashMap<String,Integer> keyMap = new HashMap<>();

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
                result.append("{\"depth\":").append(nodeTree.getDepth());
                result.append(",\"list\":");
                result.append("[").append(nodeTree.getRoot().getJson()).append("]}");
            }else{
                throw new Exception("파일 없음");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return result.toString();
    }

    public void Save(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = req.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
        body = stringBuilder.toString();
        JsonConvert jsonConvert =  new JsonConvert();

        HashMap hashMap = jsonConvert.fromJsonToHashMap(JSON.parse(body));
        hashMap.size();
    }
}
