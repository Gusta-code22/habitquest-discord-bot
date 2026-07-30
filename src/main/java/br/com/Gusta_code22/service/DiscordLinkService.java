package br.com.Gusta_code22.service;

import br.com.Gusta_code22.client.HabitQuestApiClient;
import br.com.Gusta_code22.dto.DiscordLinkDTO;
import br.com.Gusta_code22.dto.ErrorResponseDTO;
import br.com.Gusta_code22.dto.MessageResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordLinkService {

    private final HabitQuestApiClient apiClient;
    private final ObjectMapper objectMapper;

    public String linkAccount(Long userId, String discordId){
        try {
            DiscordLinkDTO dto = new DiscordLinkDTO(userId, discordId);
            MessageResponseDTO response = apiClient.link(dto);
            return response.message();
        } catch (FeignException e) {
            try {

                ErrorResponseDTO error = objectMapper.readValue(
                        e.contentUTF8(),
                        ErrorResponseDTO.class
                );

                return error.errors().getFirst();

            } catch (Exception ex) {

                return "Erro inesperado ao processar a resposta da API.";

            }
        }
    }
}
