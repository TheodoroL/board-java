package br.com.theodorol.dto;

import java.time.OffsetDateTime;

public record CardDetail(
        Long id,
        boolean blocked,
        OffsetDateTime blockedAt,
        String blockReason,
        int blocksAmount,
        Long columnId,
        String columnName
) {
}
