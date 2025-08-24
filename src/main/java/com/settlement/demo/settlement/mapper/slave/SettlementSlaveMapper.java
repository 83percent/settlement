package com.settlement.demo.settlement.mapper.slave;

import com.settlement.demo.settlement.domain.entity.SettlementEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementSlaveMapper {

    /**
     * 정산하기 데이터 목록 조회
     * @param userId 조회 요청 사용자 ID
     * @return 사용자 ID에 해당하는 '정산하기' 데이터 목록
     */
    List<SettlementEntity> selectSettlementsByUserId(String userId);

    /**
     * Settlement ID를 기반으로 '정산하기' 데이터 조회
     * @param settlementEntity 조회 요청 사용자 ID 및 정산 ID
     * @return 사용자 ID와 Settlement ID에 해당하는 '정산하기' 데이터
     */
    List<SettlementEntity> selectSettlementsById(SettlementEntity settlementEntity);

}
