package com.yanwu.design.metrics.write.impl;

import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.store.impl.RedisMetricsStorage;
import com.yanwu.design.metrics.write.ScheduleReporter;
import com.yanwu.design.metrics.write.StatViewer;
import com.yanwu.design.metrics.write.impl.ConsoleViewer;

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
public class ConsoleReporterV3 extends ScheduleReporter {

    private MetricsStorage metricsStorage;
    private AggregatorV2 aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporterV3() {
        this(new RedisMetricsStorage(), new AggregatorV2(), new ConsoleViewer());
    }

    public ConsoleReporterV3(MetricsStorage metricsStorage, AggregatorV2 aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
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
