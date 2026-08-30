package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.HabitResponseDTO;
import br.com.Gusta_code22.dto.UserProfileDTO;
import br.com.Gusta_code22.service.HabitService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HabitCommand extends ListenerAdapter {

    private final HabitService service ;

    public void execute(SlashCommandInteractionEvent event) {


        String discordId = event.getUser().getId();

        try {

            List<HabitResponseDTO> habits = service.getHabit(discordId);

            if (habits.isEmpty()){
                event.reply("Nenhum hábito encontrado para este usuário ou a conta ainda não está vinculada.")
                        .queue();
                return;
            }

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("📋 Meus Hábitos");

            embed.setDescription(
                    "*Acompanhe seu progresso e mantenha sua sequência!*"
            );

            for (HabitResponseDTO habit : habits) {
                String descricaoHabit = habit.description() != null
                        ? habit.description()
                        : "Sem descrição";
                embed.addField(
                        "☕ **" + habit.nome() + "**",
                        "💬 " + descricaoHabit +
                                "\n🔥 Streak: **" + habit.streak() + " dia" +
                                (habit.streak() == 1 ? "" : "s") + "**",
                        false
                );
            }

            embed.setFooter("HabitQuest • Seus hábitos, sua evolução.");


            event.replyEmbeds(embed.build()).queue();

        } catch (Exception e) {

            event.reply("❌ " + e.getMessage()).queue();

        }
    }
}
