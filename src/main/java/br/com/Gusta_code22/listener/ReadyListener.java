package br.com.Gusta_code22.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
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
                        Commands.slash("ping", "Responde com Pong!"),
                        Commands.slash("api","Situação da API"),
                        Commands.slash("vincular", "Vincula sua conta do HabitQuest")
                                .addOption(OptionType.STRING,
                                        "user-id",
                                        "Seu ID do HabitQuest",
                                        true),
                        Commands.slash("desenvolvedor", "Nome de quem Desenvolveu esse sistema"),
                        Commands.slash("perfil", "Exibe as informações do usuário"),
                        Commands.slash("habitos","Exibe todos os Hábitos deste usuário"),
                        Commands.slash("criar-habito", "Cria um novo habito")
                                .addOption(OptionType.STRING,
                                        "nome",
                                        "Nome do habito",
                                        true)
                                .addOption(OptionType.STRING,
                                        "descricao",
                                        "Descricao do habito",
                                        false),
                        Commands.slash("check-in", "Faz check-in em algum hábito")
                                .addOption(
                                        OptionType.STRING,
                                        "habit",
                                        "Escolha o hábito",
                                        true,
                                        true
                                ),
                        Commands.slash("deletar-habito", "Delete o habito escolhido")
                                .addOption(
                                        OptionType.STRING,
                                        "habit",
                                        "Escolha o hábito",
                                        true,
                                        true
                                )

                )
                .queue();

        System.out.println("Comandos registrados!");
    }
}
