package com.example.demo.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.model.StringModel;
import com.example.demo.domain.model.request.MultiStringRequest;
import com.example.demo.domain.model.request.StringRequest;
import com.example.demo.domain.model.response.StringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisString {
    private final RedisCommon redis;

    public void set(StringRequest req) {
        String key = req.baseRequest().Key();
        StringModel newModel = new StringModel(key, req.Name());

        redis.setData(key, newModel);
    }

    public StringResponse get(String key) {
        StringModel result = redis.getData(key, StringModel.class);

        List<StringModel> res = new ArrayList<StringModel>();
        if (result != null) {
            res.add(result);
        }

        return new StringResponse(res);
    }

    public void multiSet(MultiStringRequest req) {
        Map<String, Object> dataMap = new HashMap<>();

        for (int i = 0; i < req.Names().length; i++) {
            String name = req.Names()[i];
            String key = "key:" + (i + 1);

            StringModel newModel = new StringModel(key, name);

            dataMap.put(key, newModel);
        }

        redis.multiSetData(dataMap);
    }
}
