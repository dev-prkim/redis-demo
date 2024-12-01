package com.example.demo.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.string.model.HashModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RedisHash {
    private final RedisCommon redis;

    public void putInHash(String key, String field, String name) {
        HashModel model = new HashModel(field, name);
        redis.putInHash(key, field, model);
    }

    public HashModel getInHash(String key, String field) {
        return redis.getFromHash(key, field, HashModel.class);
    }

}
