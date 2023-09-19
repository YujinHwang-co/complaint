package com.personal.complaint.server.model;

import lombok.Data;

@Data
public class PagingVo {
    // 페이지
    int page;
    // 페이지 사이즈
    int pageSize;
    // (page-1) * pageSize : 페이지 시작지점
    int offSet;
    public int getOffSet() {
        return (page-1) * pageSize;
    }
}
