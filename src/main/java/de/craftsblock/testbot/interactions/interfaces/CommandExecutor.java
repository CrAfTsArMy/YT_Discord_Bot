package de.craftsblock.testbot.interactions.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface CommandExecutor {

    void run(SlashCommandInteractionEvent event);

}
