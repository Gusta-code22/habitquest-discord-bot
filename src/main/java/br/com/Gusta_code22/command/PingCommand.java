package br.com.Gusta_code22.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class PingCommand extends ListenerAdapter {

    public void execute(SlashCommandInteractionEvent event){
        Long latency = event.getJDA().getGatewayPing();

        event.reply("🏓 Pong! Latência: " + latency + "ms")
                .queue();
    }
}
