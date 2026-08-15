package br.com.Gusta_code22.service;

import br.com.Gusta_code22.client.HabitQuestApiClient;
import br.com.Gusta_code22.dto.ErrorResponseDTO;
import br.com.Gusta_code22.dto.UserProfileDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final HabitQuestApiClient client;
    private final ObjectMapper objectMapper;

    public UserProfileDTO perfil(String discordId){
        try {
            return client.perfil(discordId);

        } catch (FeignException e) {

            try {
                ErrorResponseDTO error = objectMapper.readValue(
                        e.contentUTF8(),
                        ErrorResponseDTO.class
                );

                throw new RuntimeException(error.errors().getFirst());

            } catch (JsonProcessingException ex) {
                throw new RuntimeException("Não foi possível carregar o perfil.");
            }
        }
    }
}
