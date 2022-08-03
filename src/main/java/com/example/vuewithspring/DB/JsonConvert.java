package com.example.vuewithspring.DB;

import org.apache.jena.atlas.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonConvert {
    public HashMap fromJsonToHashMap(JsonObject jsonObject){

        HashMap hashMap = new LinkedHashMap();
        try{
            for(String key : jsonObject.keySet()) {
                Object item = jsonObject.get(key);
                if (item.getClass().getName().endsWith("JsonObject")) {
                    JsonObject temp = (JsonObject) item;
                    hashMap.put(key,fromJsonToHashMap(temp));
                } else if (item.getClass().getName().endsWith("JsonArray")) {
                    JsonArray jsonArray = (JsonArray) item;
                    ArrayList arrayList = new ArrayList();
                    for(int i = 0; i<jsonArray.size(); i++){
                        Object object = jsonArray.get(i);
                        if(object.getClass().getName().endsWith("JsonObject")){
                            JsonObject   temp = (JsonObject) object;
                            HashMap map = fromJsonToHashMap(temp);
                            arrayList.add(map);
                        }else {
                            if(item instanceof JsonNull) {
                                arrayList.add(null);
                            } else {
                                arrayList.add(((JsonString) object).value());
                            }
                        }
                    }
                    hashMap.put(key,arrayList);

                } else {
                    String str;
                    if(item.getClass().getName().endsWith("Number")){
                        str=((JsonNumber) item).value().toString();
                    }else if(item.getClass().getName().endsWith("Boolean")){
                        str =((JsonBoolean) item).toString();
                    }else{
                        if(item instanceof JsonNull) {
                            str = null;
                        } else {
                            str = ((JsonString) item).value();
                        }
                    }

                    if(str != null)
                        hashMap.put(key, str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return hashMap;
    }
}
