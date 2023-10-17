package com.personal.complaint.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class MbrInfoVo extends PagingVo implements UserDetails{
    @Schema(description = "회원 번호")
    Long mbrSeq;
    @Schema(description = "회원 ID")
    String mbrId;
    @Schema(description = "회원 이름")
    String mbrNm;
    @Schema(description = "휴대폰번호")
    String mbtlnum;
    @Schema(description = "우편번호")
    String zip;
    @Schema(description = "주소")
    String addr;
    @Schema(description = "상세주소")
    String addrDetail;
    @Schema(description = "비밀번호")
    String password;
    @Schema(description = "가입 일시")
    Date joinDt;
    @Schema(description = "최종 수정 일시")
    Date lastMdfDt;
    @Schema(description = "재생성토큰")
    String refreshToken;
    @Schema(description = "재생성토큰 만료일자")
    Date refreshTokenExpire;
    @Schema(description = "회원 구분")
    String mbrType;
    @Schema(description = "권한")
    String authority;

    public String getAuthorityByMbrType(){
        String authority = "";
        if(StringUtils.hasText(mbrType)) {
            if (mbrType.equals("U")) {
                authority = "ROLE_USER";
            } else {
                authority = "ROLE_ADMIN";
            }
        }
        return authority;
    }
    // UserDetails Override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        if (authority != null) {
            roles.add(new SimpleGrantedAuthority(authority));
        }
        return roles;
    }
    @Override
    public String getUsername() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Override
    public boolean isEnabled() {
        return false;
    }
}
