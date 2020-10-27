package com.own.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Metrics_Gauges {
    public static Queue<String> q = new LinkedList<String>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter report = ConsoleReporter.forRegistry(registry).build();
//        每秒钟将度量指标打印在控制台上
        report.start(1, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(Metrics_Gauges.class, "queue", "size"), new Gauge<Integer>() {

            @Override
            public Integer getValue() {
                return q.size();
            }
        });
        while (true) {
            Thread.sleep(1000);
            q.add(new Date().toLocaleString());
        }


    }

}
