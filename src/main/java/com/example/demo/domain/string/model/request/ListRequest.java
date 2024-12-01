package com.example.demo.domain.string.model.request;

import com.example.demo.common.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "redis list request")
public record ListRequest(
        BaseRequest baseRequest,

        @Schema(description = "name")
        @NotBlank
        @NotNull
        String Name
) {
}
