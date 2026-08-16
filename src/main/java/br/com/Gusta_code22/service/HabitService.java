package br.com.Gusta_code22.service;

import br.com.Gusta_code22.client.HabitQuestApiClient;
import br.com.Gusta_code22.dto.ErrorResponseDTO;
import br.com.Gusta_code22.dto.HabitResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitQuestApiClient client;
    private final ObjectMapper objectMapper;

    public List<HabitResponseDTO> getHabit(String discordId){


        try {
            return client.habit(discordId);

        } catch (FeignException e) {

            try {
                ErrorResponseDTO error = objectMapper.readValue(
                        e.contentUTF8(),
                        ErrorResponseDTO.class
                );

                throw new RuntimeException(error.errors().getFirst());

            } catch (JsonProcessingException ex) {
                throw new RuntimeException("Não foi possível carregar os habitos.");
            }
        }
    }
}
