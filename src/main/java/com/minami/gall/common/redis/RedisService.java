package com.minami.gall.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisService {
    private final StringRedisTemplate srt;

    // key를 통해 value 리턴
    public String getData(String key) {
        return srt.opsForValue().get(key);
    }

    public void setData(String key, String value) { srt.opsForValue().set(key, value); }
    // 유효 시간 동안(key, value)저장
    public void setDataExpire(String key, String value, long seconds) {
        srt.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void deleteData(String key) {
        srt.delete(key);
    }
}