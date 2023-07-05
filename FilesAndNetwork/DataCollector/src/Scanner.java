import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
    List<String> jsonAndCsvFilesPaths = new ArrayList<>();
    public void scan(String path) {
        File file = new File (path);
        File[] files = file.listFiles();

        if (files.length == 0) { return; }

        for (int i = 0; i <files.length; i++) {
            if (files[i].isDirectory()) {
                scan(files[i].getAbsolutePath());
            } else if (files[i].getName().contains("json") || files[i].getName().contains("csv")) {
                jsonAndCsvFilesPaths.add(files[i].getAbsolutePath());
            }
        }
    }
}
