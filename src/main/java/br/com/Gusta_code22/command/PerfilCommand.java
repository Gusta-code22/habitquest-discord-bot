package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.UserProfileDTO;
import br.com.Gusta_code22.service.PerfilService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfilCommand extends ListenerAdapter {

    private final PerfilService service;

    public void execute(SlashCommandInteractionEvent event) {

        String discordId = event.getUser().getId();

        try {

            UserProfileDTO perfil = service.perfil(discordId);

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("👤 Perfil");

            embed.setDescription(
                    "👤 Nome: " + perfil.nome() + "\n\n" +
                            "⭐ Nível: " + perfil.nivel() + "\n\n" +
                            "⚡ XP: " + perfil.xp()
            );

            event.replyEmbeds(embed.build()).queue();

        } catch (Exception e) {

            event.reply("❌ " + e.getMessage()).queue();

        }
    }
}