package com.settlement.demo.settlement.biz;

import com.settlement.demo.settlement.domain.entity.SettlementEntity;
import com.settlement.demo.settlement.mapper.master.SettlementMasterMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class SettlementDataBiz {

    private final SettlementMasterMapper settlementMasterMapper;

    /**
     * 정산하기 데이터를 생성한다.
     * @param insertSettlementList 생성할 '정산하기' 데이터 목록
     * @throws Exception 정산하기 데이터 생성 실패
     */
    @Transactional
    public void insertSettlement(List<SettlementEntity> insertSettlementList) throws Exception {
        try {
            //// STEP 1. 정산하기 데이터 일괄 생성
            this.settlementMasterMapper.insertSettlement(insertSettlementList);

        } catch(Exception e) {
            log.error("----------- Settlement Bulk Insert Error -----------");
            e.fillInStackTrace();
            throw new Exception("Failed to insert settlement data", e);
        }
    }

    /**
     * 정산하기 데이터를 업데이트한다.
     * @param updateSettlementList 업데이트할 '정산하기' 데이터 목록
     * @throws Exception 정산하기 데이터 업데이트 실패
     */
    @Transactional
    public void updateSettlement(List<SettlementEntity> updateSettlementList) throws Exception {
        try {
            //// STEP 2. 정산하기 데이터 일괄 업데이트
            this.settlementMasterMapper.updateSettlementStatus(updateSettlementList);

        } catch(Exception e) {
            log.error("----------- Settlement Bulk Update Error -----------");
            e.fillInStackTrace();
            throw new Exception("Failed to update settlement data", e);
        }
    }


}
