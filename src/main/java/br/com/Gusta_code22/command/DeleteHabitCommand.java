package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.DeleteHabitDTO;
import br.com.Gusta_code22.dto.ExecutionDiscordDTO;
import br.com.Gusta_code22.dto.ExecutionResponseDTO;
import br.com.Gusta_code22.dto.HabitResponseDTO;
import br.com.Gusta_code22.service.CheckInService;
import br.com.Gusta_code22.service.HabitService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteHabitCommand extends ListenerAdapter {

    private final HabitService service;

    public void autocomplete(CommandAutoCompleteInteractionEvent event) {
        String discordId = event.getUser().getId();

        List<HabitResponseDTO> habits = service.getHabit(discordId);
        String textoDigitado = event.getFocusedOption().getValue();

        List<Command.Choice> choices = habits.stream()
                .filter(habit -> habit.nome()
                        .toUpperCase()
                        .contains(textoDigitado.toUpperCase()))
                .map(habit -> new Command.Choice(habit.nome(),
                        habit.id().toString()))
                .toList();
        event.replyChoices(choices).queue();
    }


    public void execute(@NotNull SlashCommandInteractionEvent event) {
        String discordId = event.getUser().getId();
        Long habitId = Long.valueOf(
                event.getOption("habit").getAsString()
        );

        DeleteHabitDTO dto = new DeleteHabitDTO(habitId, discordId);

        try {

            List<HabitResponseDTO> habits = service.getHabit(discordId);

            HabitResponseDTO habit = habits.stream()
                    .filter(h -> h.id().equals(habitId))
                    .findFirst()
                    .orElseThrow();

            String habitName = habit.nome();


            service.deleteHabit(dto);


            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("🗑️ Hábito excluído!");

            embed.setDescription(
                    "*O hábito foi removido com sucesso do HabitQuest.*"
            );

            embed.addField(
                    "☕ " + habitName,
                    "✅ Hábito excluído com sucesso!",
                    false
            );

            embed.setFooter("HabitQuest • Seus hábitos, sua evolução.");

            event.replyEmbeds(embed.build()).queue();

        } catch (Exception e) {

            event.reply("❌ " + e.getMessage()).queue();

        }
    }
}
