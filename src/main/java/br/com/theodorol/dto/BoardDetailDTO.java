package br.com.theodorol.dto;


import java.util.List;

public record BoardDetailDTO(Long id, String name, List<BoardColumDTO> columns) {
}
