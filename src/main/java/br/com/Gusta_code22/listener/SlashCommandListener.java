package br.com.Gusta_code22.listener;

import br.com.Gusta_code22.command.PingCommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlashCommandListener extends ListenerAdapter {
    private final PingCommand pingCommand;
    @Override
    public void onSlashCommandInteraction(@NonNull SlashCommandInteractionEvent event) {
        System.out.println("Recebi comando: " + event.getName());
        switch(event.getName()){

            case "ping":
                pingCommand.execute(event);
                break;

//            case "perfil":
//                handleProfile(event);
//                break;
//
//            case "checkin":
//                handleCheckin(event);
//                break;
        }
    }


}
