package com.example.demo.domain.string.controller;

import com.example.demo.domain.string.model.SortedSetModel;
import com.example.demo.domain.string.model.request.SortedSetRequest;
import com.example.demo.service.RedisSortedSet;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "string", description = "string api")
@RestController
@RequestMapping("/api/v1/sorted-set")
@RequiredArgsConstructor
public class SortedSetController {

    private final RedisSortedSet redis;

    @PostMapping("/sorted-set-collection")
    public void setSortedSet(
            @RequestBody @Valid SortedSetRequest req
    ) {
        redis.setSortedSet(req);
    }

    @GetMapping("/get-sorted-set")
    public Set<SortedSetModel> getSortedSet(
            @RequestParam @Valid String key,
            @RequestParam @Valid Float min,
            @RequestParam @Valid Float max
    ) {
        return redis.getSetDataByRange(key, min, max);
    }

    @GetMapping("/get-sorted-by-top")
    public List<SortedSetModel> getSortedSetByTop(
            @RequestParam @Valid String key,
            @RequestParam @Valid Integer n
    ) {
        return redis.getTopN(key, n);
    }

}
