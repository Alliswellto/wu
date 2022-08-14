package com.yanwu.design.metrics.collector;

import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.store.MetricsStorage;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName MetricsCollector * @Description TODO
 * @Author tako
 * @Date 16:05 2022/8/13
 * @Version 1.0
 **/
public class MetricsCollector {

    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
