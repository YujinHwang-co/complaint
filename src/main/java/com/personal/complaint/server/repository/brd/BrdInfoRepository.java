package com.personal.complaint.server.repository.brd;

import com.personal.complaint.server.model.BrdInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BrdInfoRepository {
    List<BrdInfoVo> listBrdInfo(BrdInfoVo param);
    int getTotalCountBrdInfo(BrdInfoVo param);
    BrdInfoVo getBrdInfo(BrdInfoVo param);
    int insertBrdInfo(BrdInfoVo param);
    int updateBrdInfo(BrdInfoVo param);
    int deleteBrdInfo(BrdInfoVo param);
}
