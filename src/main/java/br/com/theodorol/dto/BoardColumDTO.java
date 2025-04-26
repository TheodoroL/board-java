package br.com.theodorol.dto;

import br.com.theodorol.percistence.entity.BoardColumnKindEnum;

public record BoardColumDTO(Long id, String name, BoardColumnKindEnum kind) {
}
