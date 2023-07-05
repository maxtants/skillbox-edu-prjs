import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class WebParser {
    public Map<String, String> getLines() {
        Map<String, String> lines = new TreeMap<>();
        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io/").get();
            Elements elements = doc.select("span.js-metro-line");

            elements.forEach(element ->{
                lines.put(element.attr("data-line"), element.text());
            });

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return lines;
    }

    public Map<String, List<String>> getStations() {
        Map<String, List<String>> stationsMap = new HashMap<>();

        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io/").get();
            Elements lines = doc.select("div.js-metro-stations");

            lines.forEach(line ->{
                Elements stations = line.select("p.single-station");
                List<String> stationsList = new ArrayList<>();
                stations.forEach(station ->{
                    stationsList.add(station.text());
                });
                stationsMap.put(line.attr("data-line"), stationsList);
            });
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        return stationsMap;
    }

}
