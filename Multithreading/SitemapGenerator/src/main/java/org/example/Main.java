package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://skillbox.ru/";
        ForkJoinPool fjp = new ForkJoinPool();
        Parser parser = new Parser(url);
        Set<String> result = new TreeSet<>();
        result.add(url);
        result.addAll(fjp.invoke(parser));
        printFile(result);

    }

    public static void printFile(Set<String> result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("fileName"));
        result.forEach(r -> {
            String[] words = r.split("/");
            String str = "";
            if (words.length == 3) {
                str = r + "\n";
            } else if(words.length == 4) {
                str = "\t" + r + "\n";
            } else if(words.length == 5) {
                str = "\t\t" + r + "\n";
            }

            try {
                writer.write(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        writer.close();
    }
}