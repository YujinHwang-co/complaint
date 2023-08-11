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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MbrInfo API Controller", description = "MbrInfo controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MbrInfoController extends BaseController {
    private final MbrInfoService mbrInfoService;

    @Operation(summary = "회원정보 저장(회원 가입)", description = "회원정보 저장(회원 가입)")
    @PostMapping("/insertMbrInfo")
    public ResponseBase insertMbrInfo(@ParameterObject MbrInfoVo param, HttpServletRequest httpServletRequest) {
        ResponseBase rb = new ResponseBase();

        int result = mbrInfoService.insertMbrInfo(param, httpServletRequest);

        if( result > 0 ){
            rb.setStatusCodeMsg(1, "성공", param.getMbrNo());
        }else{
            rb.setStatusCodeMsg(0, "실패");
        }

        return rb;
    }
}


