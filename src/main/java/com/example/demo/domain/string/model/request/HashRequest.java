package com.example.demo.domain.string.model.request;

import com.example.demo.common.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "redis hash request")
public record HashRequest(
        BaseRequest baseRequest,

        @Schema(description = "field")
        @NotBlank
        @NotNull
        String Field,

        @Schema(description = "name")
        @NotBlank
        @NotNull
        String Name
) {
}
