package com.example.demo.domain.string.controller;

import com.example.demo.domain.string.model.HashModel;
import com.example.demo.domain.string.model.request.HashRequest;
import com.example.demo.service.RedisHash;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "string", description = "string api")
@RestController
@RequestMapping("/api/v1/hash")
@RequiredArgsConstructor
public class HashController {

    private final RedisHash redis;

    @PostMapping("/put-hashs")
    public void addToRight(
            @RequestBody @Valid HashRequest req
    ) {
        redis.putInHash(req.baseRequest().Key(), req.Field(), req.Name());
    }

    @GetMapping("/get-hashs")
    public HashModel getHashs(
            @RequestParam @Valid String key,
            @RequestParam @Valid String field
    ) {
        return redis.getInHash(key, field);
    }

}
