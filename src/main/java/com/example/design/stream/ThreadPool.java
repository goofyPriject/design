package com.example.design.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public void submit() {
        int max_size = 100000;
        List<String> strings = Arrays.asList("1", "2", "3", "4");
        int limit = (strings.size()+max_size-1)/max_size;
        //分片处理数据
        List<List<String>> list = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> strings.stream().skip(a * max_size).limit(max_size).parallel().collect(Collectors.toList())).collect(Collectors.toList());
        List<Future<String>> futures = new ArrayList<>();
        for (List<String> objects : list) {
            Future<String> submit = executor.submit(()-> this.execute(objects));
            futures.add(submit);
        }
        for (Future<String> future : futures) {
            try {
                String result = future.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public String execute(List<String> objects) {
        logger.info("ssss");
        return "";
    }


}
