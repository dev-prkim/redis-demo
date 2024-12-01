package com.example.demo.domain.controller;

import com.example.demo.service.RedisStrategy;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "strategy", description = "strategy api")
@RestController
@RequestMapping("/api/v1/strategy")
@RequiredArgsConstructor
public class StrategyController {

    private final RedisStrategy redis;

    @GetMapping("/lua-script")
    public void luaScript(
            @RequestParam @Valid String key1,
            @RequestParam @Valid String key2,
            @RequestParam @Valid String newKey
    ) {
        redis.luaScript(key1, key2, newKey);
    }


}
