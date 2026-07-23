package br.com.Gusta_code22.config;

import br.com.Gusta_code22.listener.ReadyListener;
import br.com.Gusta_code22.listener.SlashCommandListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DiscordBotConfig {

    private final ReadyListener readyListener;
    private final SlashCommandListener slashCommandListener;

    @Value("${discord.token}")
    private String botToken;

    @Bean
    public JDA jda() throws InterruptedException {
        // Inicializa o JDA com o Token e as intents que ativamos no portal do Discord
        return JDABuilder.createDefault(botToken)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT
                )
                .addEventListeners(readyListener,  slashCommandListener)
                .build()
                .awaitReady(); // Espera o bot conectar 100% antes de liberar o sistema
    }
}
