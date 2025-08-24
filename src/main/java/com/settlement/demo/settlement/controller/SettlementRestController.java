package com.settlement.demo.settlement.controller;

import com.settlement.demo.settlement.domain.dto.SettlementDTO;
import com.settlement.demo.settlement.service.SettlementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settlements")
@RequiredArgsConstructor
public class SettlementRestController {

    private final SettlementService settlementService;

    @Operation(
            summary = "모든 정산하기 데이터 조회",
            description = "사용자 ID를 기반으로 '정산하기' 데이터 전체 조회 및 반환"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/")
    public ResponseEntity<List<SettlementDTO>> getSettlement(
            @RequestHeader(value = "X-USER-ID", required = true) String userId
    ) {
        return ResponseEntity
                .status (HttpStatus.OK)
                .body   (this.settlementService.selectAllSettlements(userId));
    }

    @Operation(
            summary = "정산하기 데이터 조회",
            description = "Settlement ID를 기반으로 '정산하기' 데이터 조회 및 반환"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{settlementId}")
    public ResponseEntity<SettlementDTO> getSettlementById(
            @RequestHeader(value = "X-USER-ID", required = true) String userId,
            @PathVariable("settlementId") String settlementId
    ) {
        return ResponseEntity
                .status (HttpStatus.OK)
                .body   (this.settlementService.selectSettlementById(userId, settlementId));
    }

    @Operation(
            summary = "정산하기 데이터 생성",
            description = "정산하기 데이터를 생성합니다. 사용자 ID와 정산 대상 목록을 포함해야 합니다."
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping
    public ResponseEntity<SettlementDTO> createSettlement(
            @RequestHeader(value = "X-USER-ID", required = true) String userId,
            @RequestBody SettlementDTO settlementCreateDTO
    ) {

        this.settlementService.createSettlement(userId, settlementCreateDTO);

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body   (settlementCreateDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateSettlement(
            @RequestHeader(value = "X-USER-ID", required = true) String userId,
            @RequestBody SettlementDTO settlementUpdateRequest
    ) {
        this.settlementService.updateSettlement(userId, settlementUpdateRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Settlement updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSettlement(
            @RequestHeader(value = "X-USER-ID", required = true) String userId,
            @RequestParam("settlementId") String settlementId
    ) {
        // TODO : Settlement ID를 기반으로 '정산하기' 데이터 삭제

        return ResponseEntity
                .ok("Settlement deleted successfully");
    }


}
