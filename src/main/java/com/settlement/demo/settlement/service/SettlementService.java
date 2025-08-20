package com.settlement.demo.settlement.service;

import com.settlement.demo.settlement.domain.dto.SettlementDTO;

import java.util.List;

public interface SettlementService {

    /**
     * 사용자 ID를 기반으로 '정산하기' 데이터 전체 조회 및 반환
     * @param userId 조회 요청 사용자 ID
     * @return 사용자 ID에 해당하는 '정산하기' 데이터 목록
     */
    List<SettlementDTO> selectAllSettlements(String userId);

    /**
     * Settlement ID를 기반으로 '정산하기' 데이터 조회 및 반환
     * @param userId 조회 요청 사용자 ID
     * @param settlementId 조회 요청 정산 ID
     * @return 사용자 ID와 Settlement ID에 해당하는 '정산하기' 데이터
     */
    SettlementDTO selectSettlementById(String userId, String settlementId);

    /**
     * 정산하기 생성
     * @param userId 정산하기 생성 요청 사용자 ID
     * @param settlementCreateDTO 정산하기 생성 요청 데이터
     */
    void createSettlement(String userId, SettlementDTO settlementCreateDTO);


}
