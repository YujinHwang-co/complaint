package com.personal.complaint.server.repository.mbr;

import com.personal.complaint.server.model.MbrInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MbrInfoRepository {
    MbrInfoVo getMbrInfo(MbrInfoVo param);
    int insertMbrInfo(MbrInfoVo param);
    int updateMbrInfo(MbrInfoVo param);
    int deleteMbrInfo(MbrInfoVo param);
}
