import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public List<CsvData> parse(String path) {
        List<CsvData> csvDataList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> {
                String[] record = line.split(",");
                CsvData cd = new CsvData(record[0], record[1]);
                csvDataList.add(cd);
            });
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        csvDataList.remove(0);
        return csvDataList;
    }
}
