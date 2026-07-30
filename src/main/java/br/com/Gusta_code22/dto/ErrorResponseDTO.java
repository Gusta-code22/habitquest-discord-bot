package br.com.Gusta_code22.dto;

import java.util.List;

public record ErrorResponseDTO(
        String timeStamp,
        Integer code,
        String status,
        List<String> errors
) {
}