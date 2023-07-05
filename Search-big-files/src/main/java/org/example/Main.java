package org.example;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        long minSize = Long.parseLong(args[1]) * 1024 * 1024;

        TreeMap<String, Long> results = new TreeMap<>();
        FolderScan fs = new FolderScan(path, minSize);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        results = forkJoinPool.invoke(fs);
        results.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    System.out.println(trimSize(entry.getValue()) + " " + entry.getKey());
                });
    }

    public static String trimSize(Long size) {
        double x = 0;
        double y = 1024;
        DecimalFormat df = new DecimalFormat("#.#");
        if(size < y) {
            return String.valueOf(size) + " B";
        } else if (size == y || (size > y && size < Math.pow(y, 2))) {
            x = (double) size / y;
            return Math.round(x) + " Kb";
        } else if (size == Math.pow(y, 2) || (size > Math.pow(y, 2) && size < Math.pow(y, 3))) {
            x = (double) size / Math.pow(y, 2);
            return Math.round(x) + " Mb";
        } else {
            x = (double) size / Math.pow(y, 3);
            return df.format(x) + " Gb";
        }
    }
}