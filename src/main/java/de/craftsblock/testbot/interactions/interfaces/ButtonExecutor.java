package de.craftsblock.testbot.interactions.interfaces;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface ButtonExecutor {

    void run(ButtonInteractionEvent event);

}
