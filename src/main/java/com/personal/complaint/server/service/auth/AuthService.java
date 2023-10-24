package com.personal.complaint.server.service.auth;

import com.personal.complaint.server.core.jwt.TokenProvider;
import com.personal.complaint.server.core.jwt.TokenVo;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.MbrInfoVo;
import com.personal.complaint.server.repository.mbr.MbrInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final MbrInfoRepository mbrInfoRepository;
    @Transactional
    public ResponseBase<TokenVo> login(MbrInfoVo mbrInfoVo){
        return getLoginResult(mbrInfoVo);
    }

    @Transactional
    public ResponseBase getLoginResult(MbrInfoVo mbrInfoVo) {
        MbrInfoVo loginMbrVo = mbrInfoRepository.getMbrInfo(mbrInfoVo);

        // 일치하는 사용자 존재여부
        if( loginMbrVo == null ){
            return new ResponseBase<>(-2, "존재하는 회원정보가 없습니다.(ID 없음)");
        }

        // 토큰 발급 전 초기화
        TokenVo tokenVo = null;
        // 로그인한 회원 구분
        String srchMbrType = loginMbrVo.getMbrType();

        try {
            srchMbrType += loginMbrVo.getMbrId();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(srchMbrType, mbrInfoVo.getPassword());

            // 2. 실제검증 (사용자 비밀번호 체크) 이 이루어지는 부분
            // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            tokenVo = getTokenVo(authentication, mbrInfoVo);
        } catch (InternalAuthenticationServiceException ex) {
            log.error("로그인 인증", ex.toString());
            return new ResponseBase<>(-2, "존재하는 회원정보가 없습니다.(ID 없음)");
        } catch (BadCredentialsException ex) {
            log.error("로그인 인증", ex.toString());
            return new ResponseBase<>(-7, "비밀번호 불일치");
        } catch (Exception ex) {
            log.error("로그인 인증, 토큰 발급", ex.toString());
            return new ResponseBase<>(0, "로그인 실패(토큰발급불가)");
        }
        if (tokenVo != null) {
            return new ResponseBase(1,"로그인 성공", tokenVo);
        } else {
            return new ResponseBase(0, "로그인 실패");
        }
    }

    @Transactional
    public TokenVo getTokenVo(Authentication authentication, MbrInfoVo mbrInfoVo) {
        MbrInfoVo loginMbrVo = mbrInfoRepository.getMbrInfo(mbrInfoVo);

        // 3. 인증 정보로 토큰 생성
        TokenVo tokenVo;
        if (authentication != null) {
            tokenVo = tokenProvider.createToken(authentication, loginMbrVo);
        } else {
            tokenVo = tokenProvider.createToken(loginMbrVo);
        }

        /**
         * TODO: 4. refreshToken 저장
         */
        log.info("tokenVo 발급성공");

        // 5. 토큰 발급
        return tokenVo;
    }
}
