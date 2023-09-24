package com.personal.complaint.server.api.controller.brd;

import com.personal.complaint.server.global.BaseController;
import com.personal.complaint.server.global.Constants;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.BrdInfoVo;
import com.personal.complaint.server.service.brd.BrdInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BrdInfo API Controller", description = "게시글 정보 관련 BrdInfo API Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brd")
public class BrdInfoController extends BaseController {
    private final BrdInfoService brdInfoService;

    @Operation(summary = "게시글 리스트 조회", description = "게시글 리스트 조회")
    @GetMapping("/listBrdInfo")
    public ResponseBase listBrdInfo(@ParameterObject BrdInfoVo param) {
        ResponseBase rb = new ResponseBase();

        if(param.getPage() == 0) param.setPage(1);
        int currentPage = param.getPage();
        int pageSize = Constants.PAGE_SIZE;
        if(param.getPageSize() == 0) param.setPageSize(pageSize);
        
        param.setOffSet((currentPage - 1) * param.getPageSize());
        Pageable pageable = PageRequest.of(currentPage - 1, param.getPageSize());

        int totalCount = brdInfoService.getTotalCountBrdInfo(param);
        List<BrdInfoVo> list = brdInfoService.listBrdInfo(param);

        if(list != null) {
            Page<BrdInfoVo> newList = new PageImpl<>(list, pageable, totalCount);
            rb.setStatusCodeMsg(1, "성공", newList);
        } else {
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }
}
