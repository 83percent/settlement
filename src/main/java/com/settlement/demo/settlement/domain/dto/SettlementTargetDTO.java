package com.settlement.demo.settlement.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SettlementTargetDTO {

    @Schema(title = "사용자 ID", description = "정산 대상 사용자의 ID")
    private String userId;

    @Schema(title = "정산 금액", description = "정산 대상 사용자의 정산 금액", example = "10000")
    private Integer amount;

    @Schema(title = "정산 상태", examples = {"WAT", "CMP"})
    private String settlementStatusCd;
}
