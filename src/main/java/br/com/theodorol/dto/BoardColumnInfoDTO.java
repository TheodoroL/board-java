package br.com.theodorol.dto;

import br.com.theodorol.percistence.entity.BoardColumnKindEnum;

public record BoardColumnInfoDTO(Long id, int order, BoardColumnKindEnum kind) {
}
