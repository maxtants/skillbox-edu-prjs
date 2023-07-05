import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Map;


public class Result {
    WebParser wp = new WebParser();
    public void createMapJsonFile() {

        JSONObject jsonObjectStations = new JSONObject();
        wp.getStations().entrySet().forEach(entry -> {
            jsonObjectStations.put(entry.getKey(), entry.getValue());
        });

        JSONArray jsonArrayLines = new JSONArray();
        wp.getLines().entrySet().forEach(entry -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number", entry.getKey());
            jsonObject.put("name", entry.getValue());
            jsonArrayLines.add(jsonObject);
        });

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stations", jsonObjectStations);
        jsonObject.put("lines", jsonArrayLines);
        printFile(jsonObject, "result/map.json");
    }

    public void createStationsJsonFile(Map<String, String> date, Map<String, String> depth) {
        JSONArray jsonArray = new JSONArray();
        wp.getStations().entrySet().forEach(entry -> {
            entry.getValue().forEach(station -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", station);
                jsonObject.put("line", entry.getKey());
                String[] split1 = station.split(" ");
                jsonObject.put("date", date.get(split1[1]));
                jsonObject.put("depth", depth.get(split1[1]));
                jsonObject.put("hasConnection", "false");
                jsonArray.add(jsonObject);
            });
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stations", jsonArray);
        printFile(jsonObject, "result/stations.json");
    }

    private void printFile(JSONObject jsonObject, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(jsonObject);
        try (FileWriter file = new FileWriter(path)) {
            file.write(result);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
