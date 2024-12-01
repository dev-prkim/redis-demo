package com.example.demo.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.string.model.ListModel;
import com.example.demo.domain.string.model.request.ListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RedisList {
    private final RedisCommon redis;

    public void addToListRight(ListRequest req) {
        ListModel model = new ListModel(req.Name());
        redis.addToListRight(req.baseRequest().Key(), model);
    }

    public void addToListLeft(ListRequest req) {
        ListModel model = new ListModel(req.Name());
        redis.addToListLeft(req.baseRequest().Key(), model);
    }

    public List<ListModel> getAllData(String key) {
        return redis.getAllList(key, ListModel.class);
    }

}
