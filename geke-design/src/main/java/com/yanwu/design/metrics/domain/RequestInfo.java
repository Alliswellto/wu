package com.yanwu.design.metrics.domain;

import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * @ClassName RequestInfo * @Description TODO
 * @Author tako
 * @Date 16:09 2022/8/13
 * @Version 1.0
 **/
@Builder
@Accessors(chain = true)
public class RequestInfo {

    private String apiName;

    private double responseTime;

    private long timestamp;

    public RequestInfo() {
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RequestInfo(String apiName, double responseTime, long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
