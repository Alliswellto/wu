package com.yanwu.design.metrics.aggr;

import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.domain.RequestStat;

import java.util.*;

/**
 * @ClassName Aggregate * @Description 将拉取到的数据进行聚合计算
 * @Author tako
 * @Date 16:23 2022/8/13
 * @Version 1.0
 **/
public class AggregatorV2 {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMills) {
        HashMap<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfoApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfoApi, durationInMills);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMills) {
        ArrayList<Double> respTimes = new ArrayList<>();
        requestInfos.forEach(v -> respTimes.add(v.getResponseTime()));
        int count = respTimes.size();
        RequestStat requestStat = RequestStat.builder()
                .maxResponseTime(max(respTimes))
                .minResponseTime(min(respTimes))
                .avgResponseTime(avg(respTimes))
                .p999ResponseTime(percentile999(respTimes))
                .p99ResponseTime(percentile99(respTimes))
                .count(count)
                .tps((long) tps(count, durationInMills / 1000))
                .build();
        return requestStat;
    }

    private double max(List<Double> dataset) {
        return Collections.max(dataset);
    }

    private double min(List<Double> dataset) {
        return Collections.min(dataset);
    }

    private double avg(List<Double> dataset) {
        double sum = 0;
        for (double data : dataset) {
            sum += data;
        }
        double avgResp = sum / dataset.size();
        return avgResp;
    }

    private double tps(int count, double duration) {
        long tps = (long) (count / duration);
        return tps;
    }

    private double percentile999(List<Double> dataset) {
        int count = dataset.size();
        int idx999 = (int) (count * 0.999);
        return dataset.get(idx999);
    }

    private double percentile99(List<Double> dataset) {
        int count = dataset.size();
        int idx99 = (int) (count * 0.99);
        return dataset.get(idx99);
    }

    private double percentile(List<Double> dataset, double ratio) {
        int count = dataset.size();
        int idxRatio = (int) (count * ratio);
        return dataset.get(idxRatio);
    }
}
