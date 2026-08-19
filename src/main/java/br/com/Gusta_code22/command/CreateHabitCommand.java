package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.HabitCreateDTO;
import br.com.Gusta_code22.dto.HabitResponseDTO;
import br.com.Gusta_code22.service.CreateHabitService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateHabitCommand extends ListenerAdapter {

    private final CreateHabitService service;

    public void execute(SlashCommandInteractionEvent event) {

        String nome = event.getOption("nome").getAsString();

        String descricao = event.getOption("descricao") != null
                ? event.getOption("descricao").getAsString()
                : null;

        String discordId = event.getUser().getId();

        HabitCreateDTO dto = new HabitCreateDTO(
                discordId,
                nome,
                descricao
        );

        try {

            HabitResponseDTO habit = service.createHabit(dto);

            String descricaoHabit = habit.description() != null
                    ? habit.description()
                    : "Sem descrição";

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("🎉 Hábito criado!");
            embed.setDescription(
                    "*Seu novo hábito foi adicionado ao HabitQuest.*"
            );

            embed.addField(
                    "☕ " + habit.nome(),
                    "💬 " + descricaoHabit +
                            "\n🔥 Streak: **" + habit.streak() + " dias**",
                    false
            );

            embed.setFooter(
                    "HabitQuest • Sua jornada começa agora!"
            );

            event.replyEmbeds(embed.build()).queue();

        } catch (Exception e) {

            event.reply("❌ " + e.getMessage()).queue();
        }
    }
}