package com.personal.complaint.server.api.controller.auth;

import com.personal.complaint.server.core.jwt.TokenVo;
import com.personal.complaint.server.global.BaseController;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.MbrInfoVo;
import com.personal.complaint.server.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Auth API Controller", description = "인증 관련 Auth API controller")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseBase<TokenVo> login(
            @Parameter @RequestParam String mbrId
            ,@Parameter @RequestParam String password) {

        if( !StringUtils.hasText(mbrId) ){
            return new ResponseBase<>(-1, "아이디가 없습니다.");
        }
        if( !StringUtils.hasText(password) ){
            return new ResponseBase<>(-2, "비밀번호가 없습니다.");
        }
        MbrInfoVo mbrInfoVo = new MbrInfoVo();
        mbrInfoVo.setMbrId(mbrId);
        mbrInfoVo.setPassword(password);

        return authService.login(mbrInfoVo);
    }
}
