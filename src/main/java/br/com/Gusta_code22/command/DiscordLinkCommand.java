package br.com.Gusta_code22.command;

import br.com.Gusta_code22.service.DiscordLinkService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordLinkCommand extends ListenerAdapter {
    private final DiscordLinkService service;

    public void execute(SlashCommandInteractionEvent event){

        Long userId = event.getOption("user-id").getAsLong();

        String discordId = event.getUser().getId();
        String response = service.linkAccount(userId, discordId);


        event.reply(response).queue();
    }
}
