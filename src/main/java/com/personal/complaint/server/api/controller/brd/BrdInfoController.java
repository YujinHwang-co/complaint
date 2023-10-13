package com.personal.complaint.server.api.controller.brd;

import com.personal.complaint.server.global.BaseController;
import com.personal.complaint.server.global.Constants;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.BrdInfoVo;
import com.personal.complaint.server.service.brd.BrdInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "게시글 조회", description = "게시글 조회")
    @GetMapping("/getBrdInfo")
    public ResponseBase getBrdInfo(@ParameterObject BrdInfoVo param) {
        ResponseBase rb = new ResponseBase();
        
        BrdInfoVo vo = brdInfoService.getBrdInfo(param);
        if(vo != null) {
            rb.setStatusCodeMsg(1, "성공");
        } else {
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }

    @Operation(summary = "게시글 등록", description = "게시글 등록")
    @PostMapping("/insertBrdInfo")
    public ResponseBase insertBrdInfo(@ParameterObject BrdInfoVo param, HttpServletRequest httpServletRequest) {
        ResponseBase rb = new ResponseBase();

        int result = brdInfoService.insertBrdInfo(param, httpServletRequest);

        if(result > 0) {
            rb.setStatusCodeMsg(1, "성공");
        } else {
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정")
    @PostMapping("/updateBrdInfo")
    public ResponseBase updateBrdInfo(@ParameterObject BrdInfoVo param, HttpServletRequest httpServletRequest) {
        ResponseBase rb = new ResponseBase();

        int result = brdInfoService.updateBrdInfo(param, httpServletRequest);

        if(result > 0) {
            rb.setStatusCodeMsg(1, "성공");
        } else {
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }

    @Operation(summary = "게시글 삭제", description = "게시글 삭제")
    @DeleteMapping("/deleteBrdInfo")
    public ResponseBase deleteBrdInfo(@ParameterObject BrdInfoVo param) {
        ResponseBase rb = new ResponseBase();

        int result = brdInfoService.deleteBrdInfo(param);

        if(result > 0) {
            rb.setStatusCodeMsg(1, "성공");
        } else {
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }
}
