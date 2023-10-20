package com.personal.complaint.server.core.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenVo {
    @Schema(description = "인증타입, Bearer로 고정")
    private String grantType;
    @Schema(description = "접근토큰")
    private String accessToken;
    @Schema(description = "접근토큰 만료 시간")
    private Long accessTokenExpire;
}