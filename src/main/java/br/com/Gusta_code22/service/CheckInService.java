package br.com.Gusta_code22.service;

import br.com.Gusta_code22.client.HabitQuestApiClient;
import br.com.Gusta_code22.dto.ErrorResponseDTO;
import br.com.Gusta_code22.dto.ExecutionDiscordDTO;
import br.com.Gusta_code22.dto.ExecutionResponseDTO;
import br.com.Gusta_code22.dto.HabitResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final HabitQuestApiClient client;
    private final ObjectMapper objectMapper;


    public List<HabitResponseDTO> getHabits(String discordId) {
        try {
            return client.getHabit(discordId);

        } catch (FeignException e) {

            try {
                ErrorResponseDTO error = objectMapper.readValue(
                        e.contentUTF8(),
                        ErrorResponseDTO.class
                );

                throw new RuntimeException(error.errors().getFirst());

            } catch (JsonProcessingException ex) {
                throw new RuntimeException(
                        "Não foi possível carregar os hábitos."
                );
            }
        }
    }

    public ExecutionResponseDTO checkIn(ExecutionDiscordDTO dto){
        try {
            return client.checkIn(dto);

        } catch (FeignException e) {

            try {
                ErrorResponseDTO error = objectMapper.readValue(
                        e.contentUTF8(),
                        ErrorResponseDTO.class
                );

                throw new RuntimeException(error.errors().getFirst());

            } catch (JsonProcessingException ex) {
                throw new RuntimeException(
                        "Não foi possível fazer Check-in."
                );
            }
        }
    }
}
