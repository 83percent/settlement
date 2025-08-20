package com.settlement.demo.settlement.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettlementEntity {

    @Schema(title = "정산하기 ID")
    private String settlementId;

    @Schema(title = "정산하기 대상 유저 ID")
    private String userId;

    @Schema(title = "정산하기 금액", description = "정산하기 요청 받은 금액", example = "100000")
    private Integer amount;

    @Schema(title = "정산여부", examples = {"Y", "N"})
    private String settleYn;
}
