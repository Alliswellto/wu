package com.yanwu.design.metrics.write.impl;

import com.google.gson.Gson;
import com.yanwu.design.metrics.aggr.Aggregator;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;

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
public class ConsoleReporter {

    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMills = durationInSeconds * 1000;
            long endTimeInMills = System.currentTimeMillis();
            long startTimeInMills = endTimeInMills - durationInMills;
            Map<String, List<RequestInfo>> requestInfos = metricsStorage
                    .getRequestInfos(startTimeInMills, endTimeInMills);
            HashMap<String, RequestStat> stats = new HashMap<>();

            for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                String apiName = entry.getKey();
                List<RequestInfo> requestInfoApi = entry.getValue();
                RequestStat requestStat = Aggregator.aggregate(requestInfoApi, durationInMills);
                stats.put(apiName, requestStat);
            }
            System.out.println("Time Span:[" + startTimeInMills + "," + endTimeInMills + "]");
            Gson gson = new Gson();
            System.out.println(gson.toJson(stats));
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
