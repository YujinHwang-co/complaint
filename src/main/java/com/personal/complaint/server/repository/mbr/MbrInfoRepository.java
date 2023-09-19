package com.personal.complaint.server.repository.mbr;

import com.personal.complaint.server.model.MbrInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MbrInfoRepository {
    List<MbrInfoVo> listMbrInfo(MbrInfoVo param);
    int getTotalCountMbrInfo(MbrInfoVo param);
    MbrInfoVo getMbrInfo(MbrInfoVo param);
    int insertMbrInfo(MbrInfoVo param);
    int updateMbrInfo(MbrInfoVo param);
    int deleteMbrInfo(MbrInfoVo param);
}
