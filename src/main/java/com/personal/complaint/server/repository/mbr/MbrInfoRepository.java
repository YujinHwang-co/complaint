package com.personal.complaint.server.repository.mbr;

import com.personal.complaint.server.model.MbrInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MbrInfoRepository {
    int insertMbrInfo(MbrInfoVo param);
}
