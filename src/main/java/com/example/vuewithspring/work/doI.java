package com.example.vuewithspring.work;

import com.example.vuewithspring.DB.JsonConvert;
import com.example.vuewithspring.DB.Node;
import com.example.vuewithspring.DB.NodeTree;
import com.example.vuewithspring.DB.info;
import org.apache.commons.io.IOUtils;
import org.apache.jena.atlas.json.JSON;
import org.json.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class doI {
    JsonConvert jsonConvert = new JsonConvert();

    static HashMap<String,Integer> keyToMap = new HashMap<>();
    static HashMap<Integer,String> mapToKey = new HashMap<>();

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
        HashMap paramMap = (HashMap) hashMap.get("params");
        String mode = (String) paramMap.get("mode");
        ArrayList<HashMap<String,Object>> nodeList = (ArrayList) paramMap.get("nodes");

        JSONObject kvg = new JSONObject();
        JSONObject nodes = new JSONObject();
        JSONObject relation = new JSONObject();
        HashMap kvgMap= new HashMap<>();
        HashMap objectMap= new HashMap<>();
        HashMap relationMap= new HashMap<>();

        for(HashMap node : nodeList){
            String key = (String) node.get("id");
            HashMap infoMap = (HashMap) node.get("info");
            info temp = new info(infoMap);
            //            별도 검증 로직 필요
            for(Object o : infoMap.keySet()){
                String oKey = (String) o;
                if(kvgMap.containsKey(keyToMap.get(oKey))){
                    HashMap tempHashMap = (HashMap) kvgMap.get(keyToMap.get(oKey));
                    if(!tempHashMap.containsKey(infoMap.get(oKey))){
                        tempHashMap.put(infoMap.get(oKey),1);
                    }
                }else{
                    HashMap tempHashMap = new HashMap();
                    tempHashMap.put(infoMap.get(oKey),1);
                    kvgMap.put(keyToMap.get(oKey),tempHashMap);
                }
            }
        }
        kvg.put("data",kvgMap);
        String fileName = "D:/TestProject/JSONDB/"+mode+".json";
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(kvg.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String,Object>> edgeList = (ArrayList) paramMap.get("edges");
        for(HashMap edge : edgeList){
            String source = (String) edge.get("source");
            String target = (String) edge.get("target");
            //            별도 검증 로직 필요
        }

    }
//    public void Save(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        keyToMap.clear();
//        mapToKey.clear();
//        keyToMap.put("name",1);
//        keyToMap.put("type",2);
//        keyToMap.put("version",3);
//        mapToKey.put(1,"name");
//        mapToKey.put(2,"type");
//        mapToKey.put(3,"version");
//
//        String body = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        InputStream inputStream = req.getInputStream();
//
//        if (inputStream != null) {
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            char[] charBuffer = new char[128];
//            int bytesRead = -1;
//            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                stringBuilder.append(charBuffer, 0, bytesRead);
//            }
//        }
//        body = stringBuilder.toString();
//        JsonConvert jsonConvert =  new JsonConvert();
//
//        HashMap hashMap = jsonConvert.fromJsonToHashMap(JSON.parse(body));
//        HashMap paramMap = (HashMap) hashMap.get("params");
//        String mode = (String) paramMap.get("mode");
//        ArrayList<HashMap<String,Object>> nodeList = (ArrayList) paramMap.get("nodes");
//
//        JSONObject kvg = new JSONObject();
//        JSONObject nodes = new JSONObject();
//        JSONObject relation = new JSONObject();
//        HashMap kvgMap= new HashMap<>();
//        HashMap objectMap= new HashMap<>();
//        HashMap relationMap= new HashMap<>();
//
//        for(HashMap node : nodeList){
//            String key = (String) node.get("id");
//            HashMap infoMap = (HashMap) node.get("info");
//            info temp = new info(infoMap);
//            //            별도 검증 로직 필요
//            for(Object o : infoMap.keySet()){
//                String oKey = (String) o;
//                if(kvgMap.containsKey(keyToMap.get(oKey))){
//                    HashMap tempHashMap = (HashMap) kvgMap.get(keyToMap.get(oKey));
//                    if(!tempHashMap.containsKey(infoMap.get(oKey))){
//                        tempHashMap.put(infoMap.get(oKey),1);
//                    }
//                }else{
//                    HashMap tempHashMap = new HashMap();
//                    tempHashMap.put(infoMap.get(oKey),1);
//                    kvgMap.put(keyToMap.get(oKey),tempHashMap);
//                }
//            }
//        }
//        kvg.put("data",kvgMap);
//        String fileName = "D:/TestProject/JSONDB/"+mode+".json";
//        try {
//            FileWriter file = new FileWriter(fileName);
//            file.write(kvg.toString());
//            file.flush();
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<HashMap<String,Object>> edgeList = (ArrayList) paramMap.get("edges");
//        for(HashMap edge : edgeList){
//            String source = (String) edge.get("source");
//            String target = (String) edge.get("target");
//            //            별도 검증 로직 필요
//        }
//
//    }
}
