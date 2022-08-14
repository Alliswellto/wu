package com.yanwu.design.metrics.collector;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.store.MetricsStorage;
import com.yanwu.design.metrics.store.impl.RedisMetricsStorage;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.Executors;

/**
 * @ClassName MetricsCollector * @Description TODO
 * @Author tako
 * @Date 16:05 2022/8/13
 * @Version 1.0
 **/
public class MetricsCollectorV3 {

    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;

    private MetricsStorage metricsStorage;
    private EventBus eventBus;

    public MetricsCollectorV3() {
        this(new RedisMetricsStorage(), DEFAULT_STORAGE_THREAD_POOL_SIZE);;
    }

    public MetricsCollectorV3(MetricsStorage metricsStorage, int threadNumToSaveData) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newSingleThreadScheduledExecutor());
        this.eventBus.register(new EventListener());
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        eventBus.post(requestInfo);
    }

    public class EventListener {
        public void saveRequestInfo(RequestInfo requestInfo) {
            metricsStorage.saveRequestInfo(requestInfo);
        }
    }
}
