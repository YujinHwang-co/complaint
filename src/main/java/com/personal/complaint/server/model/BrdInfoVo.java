package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class BrdInfoVo extends PagingVo {
    @Schema(description = "게시글 번호")
    Long brdSeq;
    @Schema(description = "게시글 분류")
    String category;
    @Schema(description = "제목")
    String brdTitle;
    @Schema(description = "내용")
    String brdContent;
    @Schema(description = "회원 번호")
    Long mbrSeq;
    @Schema(description = "첨부파일 경로")
    String attchFilePath;
    @Schema(description = "경도")
    String longitude;
    @Schema(description = "위도")
    String latitude;
    @Schema(description = "최초 등록 일시")
    Date frstRgstDt;
    @Schema(description = "최종 수정 일시")
    Date lastMdfDt;

}