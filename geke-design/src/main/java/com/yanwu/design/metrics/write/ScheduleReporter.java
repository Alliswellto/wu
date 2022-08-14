package com.yanwu.design.metrics.write;

import com.yanwu.design.metrics.aggr.Aggregator;
import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SchedulerReporter * @Description TODO
 * @Author tako
 * @Date 19:00 2022/8/14
 * @Version 1.0
 **/
public class ScheduleReporter {

    private static final long MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000; // 10h
    protected MetricsStorage metricsStorage;
    protected AggregatorV2 aggregator;
    protected StatViewer viewer;

    public ScheduleReporter(MetricsStorage metricsStorage, AggregatorV2 aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        Map<String, RequestStat> stats = doStat(startTimeInMillis, endTimeInMillis);
        viewer.output(stats, startTimeInMillis, endTimeInMillis);
    }

    private Map<String, RequestStat> doStat(long startTimeInMillis, long endTimeInMillis) {
        Map<String, List<RequestStat>> segmentStats = new HashMap<>();
        long segmentStartTimeMillis = startTimeInMillis;
        while (segmentStartTimeMillis < endTimeInMillis) {
            long segmentEndTimeMillis = segmentStartTimeMillis + MAX_STAT_DURATION_IN_MILLIS;
            if (segmentEndTimeMillis > endTimeInMillis) {
                segmentEndTimeMillis = endTimeInMillis;
            }
            Map<String, List<RequestInfo>> requestInfos =
                    metricsStorage.getRequestInfos(segmentStartTimeMillis, segmentEndTimeMillis);
            if (requestInfos == null || requestInfos.isEmpty()) {
                continue;
            }
            Map<String, RequestStat> segmentStat = aggregator.aggregate(
                    requestInfos, segmentEndTimeMillis - segmentStartTimeMillis);
            addStat(segmentStats, segmentStat);
            segmentStartTimeMillis += MAX_STAT_DURATION_IN_MILLIS;
        }
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, RequestStat> aggregatedStats = aggregateStats(segmentStats, durationInMillis);
        return aggregatedStats;
    }

    private void addStat(Map<String, List<RequestStat>> segmentStats,
                         Map<String, RequestStat> segmentStat) {
        for (Map.Entry<String, RequestStat> entry : segmentStat.entrySet()) {
            String apiName = entry.getKey();
            RequestStat stat = entry.getValue();
            List<RequestStat> statList = segmentStats.putIfAbsent(apiName, new ArrayList<>());
            statList.add(stat);
        }
    }

    private Map<String, RequestStat> aggregateStats(Map<String, List<RequestStat>> segmentStats, long durationInMillis) {
        Map<String, RequestStat> aggregatedStats = new HashMap<>();
        for (Map.Entry<String, List<RequestStat>> entry : segmentStats.entrySet()) {
            String apiName = entry.getKey();
            List<RequestStat> apiStats = entry.getValue();
            double maxRespTime = Double.MIN_VALUE;
            double minRespTime = Double.MAX_VALUE;
            long count = 0;
            double sumRespTime = 0;
            for (RequestStat stat : apiStats) {
                if (stat.getMaxResponseTime() > maxRespTime) maxRespTime = stat.getMaxResponseTime();
                if (stat.getMinResponseTime() < minRespTime) minRespTime = stat.getMinResponseTime();
                count += stat.getCount();
                sumRespTime += (stat.getCount() * stat.getAvgResponseTime());
            }
            RequestStat aggregatedStat = RequestStat.builder()
                    .maxResponseTime(maxRespTime)
                    .minResponseTime(minRespTime)
                    .avgResponseTime(sumRespTime / count)
                    .count(count)
                    .tps(count / durationInMillis * 1000)
                    .build();
            aggregatedStats.put(apiName, aggregatedStat);
        }
        return aggregatedStats;
    }
}
