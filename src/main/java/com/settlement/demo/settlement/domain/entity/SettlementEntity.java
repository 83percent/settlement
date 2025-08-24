package com.settlement.demo.settlement.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementEntity {

    @NotBlank
    @Schema(title = "정산하기 ID")
    private String settlementId;

    @NotBlank
    @Schema(title = "정산하기 생성 유저 ID")
    private String createUserId;

    @NotBlank
    @Schema(title = "정산하기 대상 유저 ID")
    private String targetUserId;

    @Min(1)
    @Schema(title = "정산하기 금액", description = "정산하기 요청 받은 금액", example = "100000")
    private Integer amount;

    @Schema(title = "정산 상태", examples = {"WAT", "CMP"})
    private String settlementStatusCd;
}
