package br.com.Gusta_code22.client;

import br.com.Gusta_code22.dto.DiscordLinkDTO;
import br.com.Gusta_code22.dto.HealthResponse;
import br.com.Gusta_code22.dto.MessageResponseDTO;
import br.com.Gusta_code22.dto.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "habitquest-api", url = "${habitquest.api.url}")
public interface HabitQuestApiClient {

    @GetMapping("/actuator/health")
    HealthResponse getHealth();

    @PostMapping("/discord/link")
    MessageResponseDTO link(@RequestBody DiscordLinkDTO dto);

    @GetMapping("/discord/{discordId}")
    UserProfileDTO perfil(@PathVariable String discordId);

}
