import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public String jsonToString(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line));
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return sb.toString();
    }

    public List<JsonData> parse (String path) {
        List<JsonData> jsonDataList = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonToString(path));
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (path.contains("date")) {
                    JsonData jd = new JsonData(jsonObject.get("name").toString(),
                                                jsonObject.get("date").toString());
                    jsonDataList.add(jd);
                } else {
                    JsonData jd = new JsonData(jsonObject.get("name").toString(),
                            jsonObject.get("depth").toString());
                    jsonDataList.add(jd);
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return jsonDataList;
    }
}
