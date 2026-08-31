package br.com.Gusta_code22.listener;

import br.com.Gusta_code22.command.CheckInCommand;
import br.com.Gusta_code22.command.DeleteHabitCommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoCompleteListener extends ListenerAdapter {

    private final CheckInCommand CheckInCommand;
    private final DeleteHabitCommand deleteHabitCommand;


    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        switch(event.getName()){
            case "check-in":
                CheckInCommand.autocomplete(event);
                break;
            case "deletar-habito":
                deleteHabitCommand.autocomplete(event);
                break;
        }
    }
}
