package br.com.Gusta_code22.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class DesenvolvedorCommand extends ListenerAdapter {
    public void execute(SlashCommandInteractionEvent event){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("👨‍💻 Desenvolvedor");
        embed.setDescription(
                "Gustavo Miranda Brito\n" +
                        "Criador do HabitQuest\n\n" +
                        "🔗 GitHub: Gusta-code22"
        );

        event.replyEmbeds(embed.build()).queue();
    }
}
