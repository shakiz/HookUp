package com.shakil.travelpointer.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<String, String> parseJsonObject(JSONObject jsonObject){
        HashMap<String, String> dataList = new HashMap<>();
        try {
            String name = jsonObject.getString("name");
            String latitude = jsonObject
                    .getJSONObject("geometry")
                    .getJSONObject("location")
                    .getString("lat");
            String longitude = jsonObject
                    .getJSONObject("geometry")
                    .getJSONObject("location")
                    .getString("lng");
            dataList.put("name", name);
            dataList.put("lat", latitude);
            dataList.put("lng",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private List<HashMap<String, String>> parseJsonArray(JSONArray jsonArray){
        List<HashMap<String, String>> dataList = new ArrayList<>();
        for (int start = 0; start < jsonArray.length(); start++) {
            try {
                HashMap<String, String> data = parseJsonObject((JSONObject) jsonArray.get(start));
                dataList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public List<HashMap<String, String>> parseResult(JSONObject jsonObject){
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseJsonArray(jsonArray);
    }
}
