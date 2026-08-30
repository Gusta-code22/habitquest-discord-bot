package br.com.Gusta_code22.command;

import br.com.Gusta_code22.dto.ExecutionDiscordDTO;
import br.com.Gusta_code22.dto.ExecutionResponseDTO;
import br.com.Gusta_code22.dto.HabitResponseDTO;
import br.com.Gusta_code22.service.CheckInService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CheckInCommand extends ListenerAdapter {

    private final CheckInService service;

    public void autocomplete(CommandAutoCompleteInteractionEvent event) {

        String discordId = event.getUser().getId();

        List<HabitResponseDTO> habits = service.getHabits(discordId);
        String textoDigitado = event.getFocusedOption().getValue();

        List<Command.Choice> choices = habits.stream()
                .filter(habit -> habit.nome()
                        .toUpperCase()
                        .contains(textoDigitado.toUpperCase()))
                .filter(habit -> !service.jaTemCheckinHoje(habit.id()))
                .map(habit -> new Command.Choice(habit.nome(),
                        habit.id().toString()))
                .toList();
        event.replyChoices(choices).queue();
    }

    public void execute(SlashCommandInteractionEvent event) {


        String discordId = event.getUser().getId();
        Long habitId = Long.valueOf(
                event.getOption("habit").getAsString()
        );

        ExecutionDiscordDTO dto = new ExecutionDiscordDTO(discordId, habitId);

        try {


            ExecutionResponseDTO executionResponseDTO = service.checkIn(dto);


            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("🎉 Check-in realizado!");

            embed.setDescription(
                    "*Mais um passo na sua jornada. Continue mantendo a sequência!*"
            );

            embed.addField(
                    "☕ " + executionResponseDTO.habitName(),
                    "\n🔥 Streak: **" + executionResponseDTO.streak() + " dia" +
                            (executionResponseDTO.streak() == 1 ? "" : "s") + "**" +
                            "\n⭐ XP atual: **" + executionResponseDTO.xpGained() + " XP**\n" +
                            "🏆 Nível: **" + executionResponseDTO.level() + "**",
                    false
            );

            embed.setFooter("HabitQuest • Seus hábitos, sua evolução.");

            event.replyEmbeds(embed.build()).queue();

        } catch (Exception e) {

            event.reply("❌ " + e.getMessage()).queue();

        }
    }
}
