package com.personal.complaint.server.service.brd;

import com.personal.complaint.server.model.BrdInfoVo;
import com.personal.complaint.server.repository.brd.BrdInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrdInfoService {
    private final BrdInfoRepository brdInfoRepository;
    public List<BrdInfoVo> listBrdInfo(BrdInfoVo param) {
        return brdInfoRepository.listBrdInfo(param);
    }
    public int getTotalCountBrdInfo(BrdInfoVo param) {
        return brdInfoRepository.getTotalCountBrdInfo(param);
    }
    public BrdInfoVo getBrdInfo(BrdInfoVo param) {
        return brdInfoRepository.getBrdInfo(param);
    }
    public int insertBrdInfo(BrdInfoVo param, HttpServletRequest req) {
        return brdInfoRepository.insertBrdInfo(param);
    }
    public int updateBrdInfo(BrdInfoVo param, HttpServletRequest req) {
        return brdInfoRepository.updateBrdInfo(param);
    }
    public int deleteBrdInfo(BrdInfoVo param) {
        return brdInfoRepository.deleteBrdInfo(param);
    }
}
