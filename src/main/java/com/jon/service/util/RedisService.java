package com.jon.service.util;

import java.util.List;
import java.util.Set;

public interface RedisService {
    boolean set(final String key, Object value);

    boolean set(final String key, Object value, Long expireTime);

    boolean exists(final String key);

    Object get(final String key);

    void remove(final String key);

    void remove(final String... keys);

    void removePattern(final String pattern);

    void hashSet(String key, Object hashKey, Object value);

    Object hashGet(String key, Object hashKey);

    void push(String k,Object v);

    List<Object> range(String k, long l, long l1);

    void setAdd(String key,Object value);

    Set<Object> setMembers(String key);

    void zAdd(String key,Object value,double scoure);

    Set<Object> rangeByScore(String key,double scoure,double scoure1);
}
