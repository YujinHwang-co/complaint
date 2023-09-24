package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagingVo {
    @Schema(description = "검색 컬럼 명")
    String searchKey;
    @Schema(description = "검색어")
    String searchValue;
    @Schema(description = "페이지")
    int page;
    @Schema(description = "페이지 사이즈")
    int pageSize;
    @Schema(description = "페이지 시작지점 (page-1) * pageSize")
    int offSet;
    public int getOffSet() {
        return (page-1) * pageSize;
    }
}
