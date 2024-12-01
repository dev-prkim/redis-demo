package com.example.demo.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.string.model.SortedSetModel;
import com.example.demo.domain.string.model.request.SortedSetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class RedisSortedSet {
    private final RedisCommon redis;

    public void setSortedSet(SortedSetRequest req) {
        SortedSetModel model = new SortedSetModel(req.Name(), req.Score());
        redis.addToSortedSet(req.baseRequest().Key(), model, req.Score());
    }

    public Set<SortedSetModel> getSetDataByRange(String key, Float min, Float max) {
        return redis.rangeByScore(key, min, max, SortedSetModel.class);
    }

    public List<SortedSetModel> getTopN(String key, Integer n) {
        return redis.getTopNFromSortedSet(key, n, SortedSetModel.class);
    }

}
