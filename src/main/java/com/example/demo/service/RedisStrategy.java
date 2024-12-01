package com.example.demo.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.model.StringModel;
import com.example.demo.domain.model.ValueWithTTL;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 캐싱 전략 관련 샘플 서비스 코드
 */
@Service
@RequiredArgsConstructor
public class RedisStrategy {
    private final RedisCommon redis;

    public StringModel simpleStrategy(String key) {
        StringModel model = redis.getData(key, StringModel.class);

        if(model == null) {
            // DB를 조회한 값이라고 가정
            StringModel fromDbData = new StringModel(key, "new db");

            redis.setData(key, fromDbData);

            return fromDbData;
        }

        return model;
    }

    public StringModel perStrategy(String key) {
        ValueWithTTL<StringModel> valueWithTTL = redis.getValueWithTTL(key, StringModel.class);

        if(valueWithTTL != null) {
            // 확률적 알고리즘 실행
            asyncPerStrategy(key, valueWithTTL.getTtl());
            return valueWithTTL.getValue();
        }

        // DB를 조회한 값이라고 가정
        StringModel fromDbData = new StringModel(key, "new db");

        redis.setData(key, fromDbData);

        return fromDbData;
    }

    // 확률적 알고리즘
    @Async
    private void asyncPerStrategy(String key, Long remainTTL) {

        double probability = calculateProbability(remainTTL);

        Random random = new Random();

        if(random.nextDouble() < probability) {
            StringModel fromDB = new StringModel(key, "db from");
            redis.setData(key, fromDB);
        }

    }

    // 데이터를 갱신할지 여부를 계산
    private double calculateProbability(Long remainTTL) {
        // 너무 많이 갱신이 된다면 비율 조정 필요
        double base = 0.5;
        double decayRate = 0.1;

        return base * Math.pow(Math.E, -decayRate * remainTTL);
    }

    public void luaScript(String key1, String key2, String newKey) {
        redis.sumTwoKeyAndRenew(key1, key2, newKey);
    }
}
