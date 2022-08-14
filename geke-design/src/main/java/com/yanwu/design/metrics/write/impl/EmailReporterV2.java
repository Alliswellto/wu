package com.yanwu.design.metrics.write.impl;

import com.yanwu.design.metrics.EmailSender;
import com.yanwu.design.metrics.aggr.Aggregator;
import com.yanwu.design.metrics.aggr.AggregatorV2;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.write.StatViewer;

import java.util.*;

/**
 * @ClassName EmailReporter * @Description TODO
 * @Author tako
 * @Date 17:23 2022/8/13
 * @Version 1.0
 **/
public class EmailReporterV2 {

    private static final Long DAY_HOUR_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private AggregatorV2 aggregator;
    private StatViewer viewer;

    public EmailReporterV2(MetricsStorage metricsStorage, AggregatorV2 aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMills = DAY_HOUR_IN_SECONDS * 1000;
                long endTimeInMills = System.currentTimeMillis();
                long startTimeInMills = endTimeInMills - durationInMills;
                Map<String, List<RequestInfo>> requestInfos = metricsStorage
                        .getRequestInfos(startTimeInMills, endTimeInMills);
                HashMap<String, RequestStat> stats = new HashMap<>();
                Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMills);
                viewer.output(requestStats, startTimeInMills, endTimeInMills);
            }
        }, firstTime, DAY_HOUR_IN_SECONDS * 1000);
    }
}
