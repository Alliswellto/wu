package com.yanwu.design.metrics.write.impl;

import com.google.gson.Gson;
import com.yanwu.design.metrics.aggr.Aggregator;
import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.write.StatViewer;
import com.yanwu.design.metrics.write.impl.ConsoleViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ConsoleReporter * @Description TODO
 * @Author tako
 * @Date 16:47 2022/8/13
 * @Version 1.0
 **/
public class ConsoleReporterV2 {

    private MetricsStorage metricsStorage;
    private AggregatorV2 aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporterV2(MetricsStorage metricsStorage, AggregatorV2 aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMills = durationInSeconds * 1000;
            long endTimeInMills = System.currentTimeMillis();
            long startTimeInMills = endTimeInMills - durationInMills;
            Map<String, List<RequestInfo>> requestInfos = metricsStorage
                    .getRequestInfos(startTimeInMills, endTimeInMills);
            Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMills);
            viewer.output(requestStats, startTimeInMills, endTimeInMills);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
