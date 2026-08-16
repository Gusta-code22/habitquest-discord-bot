package br.com.Gusta_code22.dto;

import java.time.LocalDateTime;

public record HabitResponseDTO(
        String nome,
        String description,
        Integer streak,
        LocalDateTime creationDate) {
}
