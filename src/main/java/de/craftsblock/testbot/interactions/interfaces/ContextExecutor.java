package de.craftsblock.testbot.interactions.interfaces;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public interface ContextExecutor {

    default void run(MessageContextInteractionEvent event) {};
    default void run(UserContextInteractionEvent event) {};

}
