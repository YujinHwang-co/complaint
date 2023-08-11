package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

// @EqualsAndHashCode(callSuper = true)
@Data
public class MbrInfoVo {
    @Schema(description = "회원 번호")
    Long mbrNo;
    @Schema(description = "회원 ID")
    @NotBlank
    String mbrId;
    @Schema(description = "비밀번호")
    @NotBlank
    String password;
    @Schema(description = "가입 일시")
    Date joinDt;
}
