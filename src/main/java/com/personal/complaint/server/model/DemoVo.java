package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class DemoVo {
    @Schema(description = "테스트용vo1, example = \"test1\"")
    String test1;
    @Schema(description = "테스트용vo2, example = \"test2\"")
    String test2;
    @Schema(description = "테스트용vo3, example = \"test3\"")
    String test3;

    @Schema(description = "parameter valid 이메일 형식 입력 테스트")
    @Email
    String emailTest3;
}
