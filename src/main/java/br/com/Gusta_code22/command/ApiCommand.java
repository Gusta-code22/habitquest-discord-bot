package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.HealthResponse;
import br.com.Gusta_code22.service.ApiStatusService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiCommand extends ListenerAdapter {

    private final ApiStatusService service;

    public void execute(SlashCommandInteractionEvent event){
        String mensagem = service.checkApi();
        event.reply(mensagem).queue();
    }
}
