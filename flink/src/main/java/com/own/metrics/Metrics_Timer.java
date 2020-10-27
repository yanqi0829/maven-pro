package com.own.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Timer其实是 Histogram 和 Meter 的结合， histogram 某部分代码/调用的耗时， meter统计TPS。
 *
 * 一般情况下，当我们需要统计某个函数被调用的频率（TPS），会使用Meters。
 * 当我们需要统计某个函数的执行耗时时，会使用Histograms。当我们既要统计TPS又要统计耗时时，
 * 我们会使用Timers。
 */
public class Metrics_Timer {
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Timer timer = registry.timer(MetricRegistry.name(Metrics_Timer.class,"get-latency"));

        Timer.Context ctx;

        while(true){
            ctx = timer.time();
            Thread.sleep(random.nextInt(1000));
            ctx.stop();
        }

    }
}
