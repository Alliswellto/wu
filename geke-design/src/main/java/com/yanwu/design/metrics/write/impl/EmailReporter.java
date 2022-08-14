package com.yanwu.design.metrics.write.impl;

import com.yanwu.design.metrics.aggr.Aggregator;
import com.yanwu.design.metrics.EmailSender;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.store.MetricsStorage;

import java.util.*;

/**
 * @ClassName EmailReporter * @Description TODO
 * @Author tako
 * @Date 17:23 2022/8/13
 * @Version 1.0
 **/
public class EmailReporter {

    private static final Long DAY_HOUR_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailReporter(MetricsStorage metricsStorage) {
        this(metricsStorage, new EmailSender());
    }

    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender) {
        this.metricsStorage = metricsStorage;
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
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
                for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                    String apiName = entry.getKey();
                    List<RequestInfo> requestInfoApi = entry.getValue();
                    RequestStat requestStat = Aggregator.aggregate(requestInfoApi, durationInMills);
                    stats.put(apiName, requestStat);
                }
                // TODO: 格式化为 html 格式，并且发送 email
            }
        }, firstTime, DAY_HOUR_IN_SECONDS * 1000);
    }
}
