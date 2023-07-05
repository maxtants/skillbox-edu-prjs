import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        new Result().createMapJsonFile();

        Scanner scanner = new Scanner();
        scanner.scan("data/");
        List<String> paths = scanner.jsonAndCsvFilesPaths;
        new Result().createStationsJsonFile(dateMap(paths), depthMap(paths));
    }

    public static Map<String, String> dateMap(List<String> paths) {
        Map<String, String> date = new HashMap<>();
        paths.forEach(path -> {
            if (path.contains("date") && path.contains("json")) {
                List<JsonData> list = new JsonParser().parse(path);
                list.forEach(e -> {
                    date.put(e.name, e.value);
                });
            } else if (path.contains("date") && path.contains("csv")) {
                List<CsvData> list = new CsvParser().parse(path);
                list.forEach(e -> {
                    date.put(e.name, e.value);
                });
            }
        });
        return date;
    }

    public static Map<String, String> depthMap(List<String> paths) {
        Map<String, String> depth = new HashMap<>();
        paths.forEach(path -> {
            if (path.contains("depth") && path.contains("json")) {
                List<JsonData> list = new JsonParser().parse(path);
                list.forEach(e -> {
                    depth.put(e.name, e.value);
                });
            } else if (path.contains("depth") && path.contains("csv")) {
                List<CsvData> list = new CsvParser().parse(path);
                list.forEach(e -> {
                    depth.put(e.name, e.value);
                });
            }
        });
        return depth;
    }
}
