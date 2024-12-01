package com.example.demo.domain.string.controller;

import com.example.demo.domain.string.model.ListModel;
import com.example.demo.domain.string.model.request.ListRequest;
import com.example.demo.service.RedisList;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "string", description = "string api")
@RestController
@RequestMapping("/api/v1/list")
@RequiredArgsConstructor
public class ListController {

    private final RedisList redis;

    @PostMapping("/add-to-right")
    public void addToRight(
            @RequestBody @Valid ListRequest req
    ) {
        redis.addToListRight(req);
    }

    @PostMapping("/add-to-left")
    public void addToLeft(
            @RequestBody @Valid ListRequest req
    ) {
        redis.addToListLeft(req);
    }

    @GetMapping("/get-all")
    public List<ListModel> getAllData(
            @RequestParam @Valid String key
    ) {
        return redis.getAllData(key);
    }

}
