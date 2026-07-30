package br.com.Gusta_code22.listener;

import br.com.Gusta_code22.command.ApiCommand;
import br.com.Gusta_code22.command.DiscordLinkCommand;
import br.com.Gusta_code22.command.PingCommand;
import br.com.Gusta_code22.service.DiscordLinkService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SlashCommandListener extends ListenerAdapter {
    private final PingCommand pingCommand;
    private final ApiCommand apiCommand;
    private final DiscordLinkCommand discordLinkCommand;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("Recebi comando: " + event.getName());
        switch(event.getName()){

            case "ping":
                pingCommand.execute(event);
                break;

            case "api":
                apiCommand.execute(event);
                break;
            case "vincular":
                discordLinkCommand.execute(event);
                break;

        }
    }


}
