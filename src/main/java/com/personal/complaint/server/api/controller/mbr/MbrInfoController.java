package com.personal.complaint.server.api.controller.mbr;

import com.personal.complaint.server.global.BaseController;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.MbrInfoVo;
import com.personal.complaint.server.service.mbr.MbrInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MbrInfo API Controller", description = "회원정보 관련 MbrInfo API controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mbr")
public class MbrInfoController extends BaseController {
    private final MbrInfoService mbrInfoService;

    @Operation(summary = "회원정보 상세 조회", description = "회원정보 상세 조회")
    @GetMapping("/getMbrInfo")
    public ResponseBase getMbrInfo(@ParameterObject MbrInfoVo param) {
        ResponseBase rb = new ResponseBase();

        MbrInfoVo vo = mbrInfoService.getMbrInfo(param);
        if( vo != null ){
            rb.setStatusCodeMsg(1, "성공", vo);
        }else{
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }

    @Operation(summary = "회원정보 저장(회원 가입)", description = "회원정보 저장(회원 가입)하는 메서드")
    @PostMapping("/insertMbrInfo")
    public ResponseBase insertMbrInfo(@ParameterObject MbrInfoVo param, HttpServletRequest httpServletRequest) {
        ResponseBase rb = new ResponseBase();

        int result = mbrInfoService.insertMbrInfo(param, httpServletRequest);

        if( result > 0 ){
            rb.setStatusCodeMsg(1, "성공", param.getMbrSeq());
        }else{
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }
    @Operation(summary = "회원정보 수정", description = "회원정보 수정하는 메서드")
    @PostMapping("/updateMbrInfo")
    public ResponseBase updateMbrInfo(@ParameterObject MbrInfoVo param, HttpServletRequest httpServletRequest) {
        ResponseBase rb = new ResponseBase();

        int result = mbrInfoService.updateMbrInfo(param, httpServletRequest);

        if( result > 0 ){
            rb.setStatusCodeMsg(1, "성공");
        }else{
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }

    @Operation(summary = "회원정보 삭제", description = "회원정보 삭제하는 메서드")
    @PostMapping("/deleteMbrInfo")
    public ResponseBase deleteMbrInfo(@ParameterObject MbrInfoVo param) {
        ResponseBase rb = new ResponseBase();

        int result = mbrInfoService.deleteMbrInfo(param);

        if( result > 0 ){
            rb.setStatusCodeMsg(1, "성공");
        }else{
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }
}


