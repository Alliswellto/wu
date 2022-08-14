package com.yanwu.design.metrics.domain;

import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * @ClassName RequestStat * @Description TODO
 * @Author tako
 * @Date 16:25 2022/8/13
 * @Version 1.0
 **/
@Accessors(chain = true)
@Builder
public class RequestStat {

    private double maxResponseTime;

    private double minResponseTime;

    private double avgResponseTime;

    private double p999ResponseTime;

    private double p99ResponseTime;

    private long count;

    private long tps;

    public double getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(double maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public double getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(double minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public double getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public double getP999ResponseTime() {
        return p999ResponseTime;
    }

    public void setP999ResponseTime(double p999ResponseTime) {
        this.p999ResponseTime = p999ResponseTime;
    }

    public double getP99ResponseTime() {
        return p99ResponseTime;
    }

    public void setP99ResponseTime(double p99ResponseTime) {
        this.p99ResponseTime = p99ResponseTime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTps() {
        return tps;
    }

    public void setTps(long tps) {
        this.tps = tps;
    }
}
