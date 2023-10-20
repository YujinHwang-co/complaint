package com.personal.complaint.server.core.jwt;

import com.personal.complaint.server.model.MbrInfoVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 ; // 1000 * 60 * 60 * 12 12시간
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenVo createToken(Authentication authentication, MbrInfoVo vo) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        vo.setAuthority(authorities);

        return createToken(vo);
    }

    public TokenVo createToken(MbrInfoVo vo) {
        String authorities = vo.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date accessTokenExpire = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(vo.getMbrId())
                .claim("mbrSeq", vo.getMbrSeq())
                .claim("mbrNm", vo.getMbrNm())
                .claim("mbrType", vo.getMbrType())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpire)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        /**
         * TODO: Refresh Token 생성
          */
        return TokenVo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpire(accessTokenExpire.getTime())
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        MbrInfoVo vo = new MbrInfoVo();

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        } else {
            vo.setAuthority(claims.get(AUTHORITIES_KEY).toString());
        }
        if (claims.get("mbrSeq") != null) {
            vo.setMbrSeq(Long.valueOf(claims.get("mbrSeq").toString()));
        }
        if (claims.get("mbrNm") != null) {
            vo.setMbrNm(claims.get("mbrNm").toString());
        }
        if (claims.get("mbrType") != null) {
            vo.setMbrType(claims.get("mbrType").toString());
        }
        if (claims.getSubject() != null) {
            vo.setMbrId(claims.getSubject());
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(vo, accessToken, authorities);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}