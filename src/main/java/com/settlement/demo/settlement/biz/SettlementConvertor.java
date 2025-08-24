package com.settlement.demo.settlement.biz;

import com.settlement.demo.settlement.domain.dto.SettlementDTO;
import com.settlement.demo.settlement.domain.dto.SettlementTargetDTO;
import com.settlement.demo.settlement.domain.entity.SettlementEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class SettlementConvertor {

    /**
     * 정산하기 Entity -> DTO 변환
     * @param settlementEntityList 정산하기 Entity List
     * @return 정산하기 DTO List
     */
    public List<SettlementDTO> convertEntityToDTO(@NonNull List<SettlementEntity> settlementEntityList) {
        log.debug("----------- Settlement Convert Entity to DTO -----------");
        HashMap<String, SettlementDTO> settlementMemory = new HashMap<>();

        settlementEntityList.forEach(element -> {

            //// 정산하기 DTO 생성
            if(!settlementMemory.containsKey(element.getSettlementId())) {
                SettlementDTO settlementDTO = SettlementDTO.builder()
                        .settlementId   (element.getSettlementId()  )
                        .createUserId   (element.getCreateUserId()  )
                        .totalAmout     (0                          )
                        .targetList     (new ArrayList<>()          )
                        .build();
                settlementMemory.put(element.getSettlementId(), settlementDTO);
            }

            SettlementDTO targetSettlementDTO = settlementMemory.get(element.getSettlementId());

            //// 정산하기 총 금액 설정
            targetSettlementDTO
                    .setTotalAmout(targetSettlementDTO.getTotalAmout() + element.getAmount());

            //// 정산하기 상세 항목 설정
            targetSettlementDTO.getTargetList().add(
                    SettlementTargetDTO.builder()
                                    .userId(element.getTargetUserId())
                                    .amount(element.getAmount())
                                    .build()
            );

        });

        return new ArrayList<>(settlementMemory.values());
    }

    /**
     * 정산하기 DTO -> Entity 변환
     * @param settlementDTO 정산하기 DTO
     * @return 정산하기 Entity List
     */
    public List<SettlementEntity> convertDTOToEntity(@NonNull SettlementDTO settlementDTO) {
        log.debug("----------- Settlement Convert Dto to Entity -----------");
        //// STEP 0. Validation
        if(settlementDTO.getTargetList() == null || settlementDTO.getTargetList().isEmpty())
            throw new IllegalArgumentException("SettlementDTO의 TargetList가 비어있습니다.");

        //// CONVERT
        String settlementId = settlementDTO.getSettlementId();
        String createUserId = settlementDTO.getCreateUserId();

        return settlementDTO.getTargetList().stream().map(
                element -> SettlementEntity.builder()
                    .settlementId       (settlementId                   )
                    .createUserId       (createUserId                   )
                    .targetUserId       (element.getUserId()            )
                    .amount             (element.getAmount()            )
                    .settlementStatusCd (element.getSettlementStatusCd())
                .build()
        ).toList();

    }

}

