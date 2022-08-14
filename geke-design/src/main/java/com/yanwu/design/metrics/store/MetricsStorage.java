package com.yanwu.design.metrics.store;

import com.yanwu.design.metrics.domain.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MetricsStorage * @Description TODO
 * @Author tako
 * @Date 16:06 2022/8/13
 * @Version 1.0
 **/
public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMills, long endTimeInMills);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMills, long endTimeInMills);

}
