package com.settlement.demo.settlement.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettlementDTO {

    @Schema(title = "정산하기 ID")
    private String settlementId;

    @Schema(title = "정산하기 생성 유저 ID")
    private String createUserId;

    @Schema(title = "정산하기 총 금액", description = "정산 대상 목록 전체의 정산 금액 총합", example = "100000")
    private Integer totalAmout;

    @Schema(title = "정산 대상 목록", description = "정산 대상 사용자들의 목록")
    private List<SettlementTargetDTO> targetList;

}
