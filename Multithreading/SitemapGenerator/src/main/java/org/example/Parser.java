package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;

public class Parser extends RecursiveTask<List<String>> {
    public String url;

    public Parser(String url) {
        this.url = url;
    }


    @Override
    protected List<String> compute() {
        List<String> urlList = new ArrayList<>();
        List<Parser> tasks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select(String.format("a[href^=%s][href$=/]", url));
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!links.isEmpty()) {
                links.forEach(link ->{
                    String[] words = link.attr("href").substring(url.length()).split("/");
                    urlList.add(url + words[0] + "/");
                });
                urlList.forEach(url -> {
                    Parser task = new Parser(url);
                    task.fork();
                    tasks.add(task);

                });
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        tasks.forEach(task -> {
            urlList.addAll(task.join());
        });

        return urlList;
    }
}
