package com.yanwu.design.metrics;

import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.collector.MetricsCollector;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.store.impl.RedisMetricsStorage;
import com.yanwu.design.metrics.write.impl.ConsoleReporterV2;
import com.yanwu.design.metrics.write.impl.EmailReporterV2;
import com.yanwu.design.metrics.write.impl.ConsoleViewer;
import com.yanwu.design.metrics.write.impl.EmailViewer;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Main * @Description TODO
 * @Author tako
 * @Date 17:39 2022/8/13
 * @Version 1.0
 **/
public class MainV2 {

    public static void main(String[] args) {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        AggregatorV2 aggregator = new AggregatorV2();

        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporterV2 consoleReporter = new ConsoleReporterV2(metricsStorage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("fulfilltako@gmail.com");
        EmailReporterV2 emailReporter = new EmailReporterV2(metricsStorage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        MetricsCollector metricsCollector = new MetricsCollector(metricsStorage);
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
