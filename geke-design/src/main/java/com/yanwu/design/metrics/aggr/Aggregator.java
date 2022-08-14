package com.yanwu.design.metrics.aggr;

import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Aggregate * @Description 将拉取到的数据进行聚合计算
 * @Author tako
 * @Date 16:23 2022/8/13
 * @Version 1.0
 **/
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMills) {
        double maxResponseTime = Double.MIN_VALUE;
        double minResponseTime = Double.MAX_VALUE;
        double avgResponseTime = -1;
        double p999ResponseTime = -1;
        double p99ResponseTime = -1;
        double sumResponseTime = 0;
        long count = 0;

        for (RequestInfo requestInfo : requestInfos) {
            ++ count;
            double responseTime = requestInfo.getResponseTime();
            if (maxResponseTime < responseTime) maxResponseTime = responseTime;
            if (minResponseTime > responseTime) minResponseTime = responseTime;
            sumResponseTime += responseTime;
        }
        if (count != 0) avgResponseTime = sumResponseTime / count;

        long tps = (long) (count / durationInMills * 1000);
        Collections.sort(requestInfos, Comparator.comparing(RequestInfo::getResponseTime));

        int idx999 = (int) (count * 0.999);
        int idx99 = (int) (count * 0.99);

        if (count != 0) {
            p999ResponseTime = requestInfos.get(idx999).getResponseTime();
            p99ResponseTime = requestInfos.get(idx99).getResponseTime();
        }

        RequestStat requestStat = RequestStat.builder()
                .maxResponseTime(maxResponseTime)
                .minResponseTime(minResponseTime)
                .avgResponseTime(avgResponseTime)
                .p999ResponseTime(p999ResponseTime)
                .p99ResponseTime(p99ResponseTime)
                .count(count)
                .tps(tps)
                .build();

        return requestStat;
    }
}
