package com.yanwu.design.metrics.write;

import com.yanwu.design.metrics.domain.RequestStat;

import java.util.Map;

/**
 * @ClassName StatView * @Description TODO
 * @Author tako
 * @Date 18:14 2022/8/14
 * @Version 1.0
 **/
public interface StatViewer {

    void output(Map<String, RequestStat> requestStats, long startTimeInMills, long endTimeInMills);
}
