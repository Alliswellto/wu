package com.yanwu.design.metrics.store.impl;

import com.google.gson.Gson;
import com.yanwu.design.metrics.domain.RequestInfo;
import com.yanwu.design.metrics.store.MetricsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * @ClassName RedisMetricsStorage * @Description TODO
 * @Author tako
 * @Date 16:21 2022/8/13
 * @Version 1.0
 **/
public class RedisMetricsStorage implements MetricsStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMetricsStorage.class);

    private static volatile JedisPool jedisPool;

    public RedisMetricsStorage() {
        jedisPool = getInstance();
    }

    public JedisPool getInstance() {
        if (jedisPool == null) {
            synchronized (RedisMetricsStorage.class) {
                if (jedisPool == null) {
                    jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
                    LOGGER.info("Redis 连接完成！");
                }
            }
        }
        return jedisPool;
    }

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Gson gson = new Gson();
            jedis.zadd(requestInfo.getApiName(), requestInfo.getTimestamp(), gson.toJson(requestInfo));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMills, long endTimeInMills) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> strs = jedis.zrangeByScore(apiName, startTimeInMills, endTimeInMills);
            ArrayList<RequestInfo> requestInfos = new ArrayList<>();
            Gson gson = new Gson();
            for (String str : strs) {
                requestInfos.add(gson.fromJson(str, RequestInfo.class));
            }
            return requestInfos;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMills, long endTimeInMills) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> keys = jedis.keys("*");
            Gson gson = new Gson();
            HashMap<String, List<RequestInfo>> map = new HashMap<>();
            for (String key : keys) {
                Set<String> strs = jedis.zrangeByScore(key, startTimeInMills, endTimeInMills);
                ArrayList<RequestInfo> requestInfos = new ArrayList<>();
                for (String str : strs) {
                    requestInfos.add(gson.fromJson(str, RequestInfo.class));
                }
                map.put(key, requestInfos);
            }
            return map;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
