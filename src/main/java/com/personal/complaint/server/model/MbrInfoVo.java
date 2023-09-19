package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class MbrInfoVo extends PagingVo{
    @Schema(description = "회원 번호")
    Long mbrSeq;
    @Schema(description = "회원 ID")
    String mbrId;
    @Schema(description = "회원 이름")
    String mbrNm;
    @Schema(description = "휴대폰번호")
    String mbtlnum;
    @Schema(description = "우편번호")
    String zip;
    @Schema(description = "주소")
    String addr;
    @Schema(description = "상세주소")
    String addrDetail;
    @Schema(description = "비밀번호")
    String password;
    @Schema(description = "가입 일시")
    Date joinDt;
    @Schema(description = "최종 수정 일시")
    Date lastMdfDt;
}
