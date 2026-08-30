package br.com.Gusta_code22.listener;

import br.com.Gusta_code22.command.*;
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
    private final DesenvolvedorCommand desenvolvedorCommand;
    private final PerfilCommand perfilCommand;
    private final HabitCommand habitCommand;
    private final CreateHabitCommand createHabitCommand;
    private final CheckInCommand checkInCommand;

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

            case "desenvolvedor":
                desenvolvedorCommand.execute(event);
                break;
            case "perfil":
                perfilCommand.execute(event);
                break;
            case "habitos":
                habitCommand.execute(event);
                break;
            case "criar-habito":
                createHabitCommand.execute(event);
                break;
            case "check-in":
                checkInCommand.execute(event);
                break;

        }
    }


}
