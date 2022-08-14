package com.yanwu.design.metrics;

import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.collector.MetricsCollector;
import com.yanwu.design.metrics.collector.MetricsCollectorV2;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.store.impl.RedisMetricsStorage;
import com.yanwu.design.metrics.write.ScheduleReporter;
import com.yanwu.design.metrics.write.impl.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Main * @Description TODO
 * @Author tako
 * @Date 17:39 2022/8/13
 * @Version 1.0
 **/
public class MainV3 {

    public static void main(String[] args) {
        ConsoleReporterV3 consoleReporter = new ConsoleReporterV3();
        consoleReporter.startRepeatedReport(60, 60);

        ArrayList<String> toAddresses = new ArrayList<>();
        toAddresses.add("fulfilltako@gmail.com");
        EmailReporterV3 emailReporter = new EmailReporterV3(toAddresses);
        emailReporter.startDailyReport();

        MetricsCollectorV2 metricsCollector = new MetricsCollectorV2();
        metricsCollector.recordRequest(new RequestInfo("register", 1000, 10000));
        metricsCollector.recordRequest(new RequestInfo("register", 1001, 11000));
        metricsCollector.recordRequest(new RequestInfo("register", 1002, 12000));
        metricsCollector.recordRequest(new RequestInfo("login", 1003, 13000));
        metricsCollector.recordRequest(new RequestInfo("login", 1004, 14000));

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
