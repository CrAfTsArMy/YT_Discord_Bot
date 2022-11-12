package de.craftsblock.testbot.commands;

import de.craftsblock.testbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class GreetCommand implements CommandExecutor {

    @Override
    public void run(SlashCommandInteractionEvent event) {
        event.replyModal(
                Modal.create("greet", "Wer soll gegrüßt werden?")
                        .addActionRows(
                                ActionRow.of(
                                        TextInput.create("name", "Name", TextInputStyle.SHORT)
                                                .setRequired(true)
                                                .setPlaceholder("Den Namen der Person")
                                                .build()
                                )
                        )
                        .build()
        ).queue();
    }

}
