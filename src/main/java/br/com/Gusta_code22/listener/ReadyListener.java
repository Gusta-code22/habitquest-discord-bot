package br.com.Gusta_code22.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Bot online! Registrando comandos...");

        Guild guild = event.getJDA().getGuildById("1228108413183983708");

        if (guild == null) {
            System.out.println("Servidor não encontrado!");
            return;
        }

        guild.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Responde com Pong!")
                )
                .queue();

        System.out.println("Comandos registrados!");
    }
}
