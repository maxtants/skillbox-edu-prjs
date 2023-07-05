package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.RecursiveTask;

public class FolderScan extends RecursiveTask<TreeMap<String, Long>> {
    String path;
    long minSize;

    public FolderScan(String path, long minSize) {
        this.path = path;
        this.minSize = minSize;
    }

    @Override
    protected TreeMap<String, Long> compute() {
        TreeMap<String, Long> results = new TreeMap<>();
        ArrayList<FolderScan> tasks = new ArrayList<>();

        File folder = new File(path);
        File[] files = folder.listFiles();
        if(files.length != 0) {
            for (File file : files) {
                if(file.isDirectory()) {
                    FolderScan task = new FolderScan(file.getAbsolutePath(), minSize);
                    task.fork();
                    tasks.add(task);
                } else if(file.isFile() && file.length() > minSize) {
                    results.put(file.getAbsolutePath(), file.length());
                }
            }
        }

        joinResults(tasks, results);
        return results;
    }

    public void joinResults(ArrayList<FolderScan> tasks, TreeMap<String, Long> results) {
        tasks.forEach(task -> {
            results.putAll(task.join());
        });
    }
}
