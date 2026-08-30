package br.com.Gusta_code22.client;

import br.com.Gusta_code22.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "habitquest-api", url = "${habitquest.api.url}")
public interface HabitQuestApiClient {

    @GetMapping("/actuator/health")
    HealthResponse getHealth();

    @GetMapping("/discord/perfil/{discordId}")
    UserProfileDTO perfil(@PathVariable String discordId);

    @GetMapping("/discord/habit/{discordId}")
    List<HabitResponseDTO> getHabit(@PathVariable String discordId);

    @PostMapping("/discord/link")
    MessageResponseDTO link(@RequestBody DiscordLinkDTO dto);

    @PostMapping("/discord/habit")
    HabitResponseDTO createHabit(@RequestBody HabitCreateDTO dto);

    @PostMapping("/discord/executions")
    ExecutionResponseDTO checkIn(@RequestBody ExecutionDiscordDTO dto);


}
