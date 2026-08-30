package br.com.Gusta_code22.dto;

public record ExecutionResponseDTO(
        String habitName,
        Integer streak,
        Integer xpGained,
        Integer level
) {
}
