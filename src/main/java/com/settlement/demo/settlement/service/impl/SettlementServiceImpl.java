package com.settlement.demo.settlement.service.impl;

import com.settlement.demo.common.exception.BizException;
import com.settlement.demo.settlement.biz.SettlementConvertor;
import com.settlement.demo.settlement.biz.SettlementDataBiz;
import com.settlement.demo.settlement.domain.dto.SettlementDTO;
import com.settlement.demo.settlement.domain.dto.SettlementTargetDTO;
import com.settlement.demo.settlement.domain.entity.SettlementEntity;
import com.settlement.demo.settlement.mapper.slave.SettlementSlaveMapper;
import com.settlement.demo.settlement.service.SettlementService;
import com.settlement.demo.settlement.util.SettlementIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementDataBiz     settlementDataBiz;
    private final SettlementConvertor   settlementConvertor;

    private final SettlementSlaveMapper settlementSlaveMapper;

    /**
     * 사용자 ID를 기반으로 '정산하기' 데이터 전체 조회 및 반환
     * @param userId 조회 요청 사용자 ID
     * @return 사용자 ID에 해당하는 '정산하기' 데이터 목록
     */
    public List<SettlementDTO> selectAllSettlements(String userId) {

        //// STEP 1. 정상하기 데이터 조회
        List<SettlementEntity> settlementEntityList = this.settlementSlaveMapper.selectSettlementsByUserId(userId);

        //// STEP 2 DTO Convert
        Map<String, SettlementDTO> settlementDTOMap = new HashMap<>();
        settlementEntityList.forEach(element -> {
            String settlementId = element.getSettlementId();

            if(!settlementDTOMap.containsKey(settlementId)) {
                SettlementDTO settlementDTO =
                        SettlementDTO.builder()
                            .settlementId   (settlementId       )
                            .createUserId   (userId             )
                            .totalAmout     (0                  )
                            .targetList     (new ArrayList<>()  )
                            .build();

                settlementDTOMap.put(settlementId, settlementDTO);
            }

            SettlementTargetDTO targetDTO = SettlementTargetDTO.builder()
                    .userId(element.getTargetUserId())
                    .amount(element.getAmount())
                    .build();

            settlementDTOMap.get(settlementId).getTargetList().add(targetDTO);

        });

        return new ArrayList<>(settlementDTOMap.values());
    }

    /**
     * Settlement ID를 기반으로 '정산하기' 데이터 조회 및 반환
     * @param userId 조회 요청 사용자 ID
     * @param settlementId 조회 요청 정산 ID
     * @return 사용자 ID와 Settlement ID에 해당하는 '정산하기' 데이터
     */
    public SettlementDTO selectSettlementById(String userId, String settlementId) {
        //// STEP 1. 정산하기 조회
        List<SettlementEntity> settlementEntityList = settlementSlaveMapper.selectSettlementsById(
                SettlementEntity.builder()
                        .createUserId(userId)
                        .settlementId(settlementId)
                        .build()
        );

        //// RESULT:  404 NOT FOUND
        if(settlementEntityList.isEmpty())
            throw new NoSuchElementException("Settlement ID not found: " + settlementId);

        return this.settlementConvertor.convertEntityToDTO(settlementEntityList).get(0);
    }

    /**
     * 정산하기 생성
     * @param userId 정산하기 생성 요청 사용자 ID
     * @param settlementCreateDTO 정산하기 생성 요청 데이터
     */
    public void createSettlement(String userId, SettlementDTO settlementCreateDTO) {

        //// STEP 1. 정산 대상의 유효성 확인
        settlementCreateDTO.getTargetList().forEach(element -> {
            // TODO : 정산 대상의 유효성 확인 로직 구현
            // userId가 실제 유효한 Id인지 확인

        });

        //// STEP 2. 정산 ID 생성
        String settlementId = SettlementIdGenerator.generate();
        settlementCreateDTO.setSettlementId(settlementId);

        //// STEP 3. 생성
        List<SettlementEntity> settlementEntityList = this.settlementConvertor.convertDTOToEntity(settlementCreateDTO);

        //// STEP 4. DB INSERT
        try {
            this.settlementDataBiz.insertSettlement(settlementEntityList);

        } catch(Exception e) {
            throw new BizException("Failed to create settlement");

        }

    }
}
