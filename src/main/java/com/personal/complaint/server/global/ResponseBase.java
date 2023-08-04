package com.personal.complaint.server.global;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ResponseBase<T> {

    @Schema(description = "결과 값(-99:유효성 검사 실패, -2:중복, -1:일치정보없음, 0:실패,오류, 1:성공)")
    private int statusCode;

    @Schema(description = "결과 메시지")
    private String statusMsg;

    @Schema(description = "결과 데이터")
    private T data;

    public ResponseBase() {
        this.statusCode = 1;
        this.statusMsg = "성공";
    }

    public ResponseBase(int statusCode, String statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }

    public ResponseBase(int statusCode, String statusMsg, T data){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }

    public void setStatusCodeMsg(int statusCode, String statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public void setStatusCodeMsg(int statusCode, String statusMsg, T data){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }
}