package com.settlement.demo.settlement.mapper.master;


import com.settlement.demo.settlement.domain.entity.SettlementEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementMasterMapper {

    /**
     * 정산하기 데이터 생성
     * @param insertSettlementList 생성할 '정산하기' 데이터 목록
     */
    void insertSettlement(List<SettlementEntity> insertSettlementList);

    /**
     * 정산하기 상태 업데이트
     * @param updatedSettlementEntity 업데이트할 '정산하기' 데이터 목록
     */
    void updateSettlementStatus(List<SettlementEntity> updatedSettlementEntity);

}
