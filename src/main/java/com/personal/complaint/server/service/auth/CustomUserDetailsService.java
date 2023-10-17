package com.personal.complaint.server.service.auth;

import com.personal.complaint.server.model.MbrInfoVo;
import com.personal.complaint.server.repository.mbr.MbrInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MbrInfoRepository mbrInfoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String mbrId = username.substring(1);

        MbrInfoVo mbrInfoVo = new MbrInfoVo();
        mbrInfoVo.setMbrId(mbrId);
        MbrInfoVo vo = mbrInfoRepository.getMbrInfo(mbrInfoVo);

        if(vo == null) {
            throw new UsernameNotFoundException(mbrId + "사용자를 찾을 수 없습니다.");
        } else {
            if(vo.getMbrType().equals("U")) {
                return createUserDetails(vo);
            } else {
                return createAdminDetails(vo);
            }
        }

    }
    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴 -> USER, ADMIN
    private UserDetails createUserDetails(MbrInfoVo vo) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(vo.getAuthorityByMbrType());
        return new User(
                String.valueOf(vo.getMbrId()),
                vo.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    private UserDetails createAdminDetails(MbrInfoVo vo) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(vo.getAuthorityByMbrType());
        return new User(
                String.valueOf(vo.getMbrId()),
                vo.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
