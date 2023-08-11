package com.personal.complaint.server.service.mbr;

import com.personal.complaint.server.model.MbrInfoVo;
import com.personal.complaint.server.repository.mbr.MbrInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class MbrInfoService {
    private final PasswordEncoder passwordEncoder;
    private final MbrInfoRepository mbrInfoRepository;

    @Transactional
    public int insertMbrInfo(MbrInfoVo param, HttpServletRequest req) {
        int result = 0;

        // password 암호화
        if(StringUtils.hasText(param.getPassword())) {
            String pwd = passwordEncoder.encode(param.getPassword());
            param.setPassword(pwd);
        }

        result =  mbrInfoRepository.insertMbrInfo(param);

        return result;
    }

}
