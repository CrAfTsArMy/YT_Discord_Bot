package de.craftsblock.testbot.commands;

import de.craftsblock.testbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

public class TestCommand implements CommandExecutor {

    @Override
    public void run(SlashCommandInteractionEvent event) {
        event.reply("Test!").addActionRow(
//                Button.danger("random", "Ändere die Nachricht"),
                SelectMenu.create("role")
                        .setPlaceholder("Wähle deine Rollen")
                        .addOption("Anfänger", "1034532317202485398", Emoji.fromCustom("new", 1034534392674123796L, false))
                        .addOption("Fortgeschritten", "1034532409649148024", Emoji.fromCustom("advanced", 1034534391306801152L, false))
                        .addOption("Java", "1034532471716466709", Emoji.fromCustom("java", 1034534388328837141L, false))
                        .addOption("JavaScript", "1034532530042450011", Emoji.fromCustom("javascript", 1034534389893320736L, false))
                        .addOption("PHP", "1034532569389207645", Emoji.fromCustom("php", 1034534387066343505L, false))
                        .addOption("HTML", "1034532588276174879", Emoji.fromCustom("html", 1034534385829040148L, false))
                        .setMinValues(2)
                        .setMaxValues(6)
                        .build()
        ).queue();
    }

}
