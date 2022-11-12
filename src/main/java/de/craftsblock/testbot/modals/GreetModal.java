package de.craftsblock.testbot.modals;

import de.craftsblock.testbot.interactions.interfaces.ModalExecutor;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class GreetModal implements ModalExecutor {

    @Override
    public void run(ModalInteractionEvent event) {
        event.reply("Hallo, " + event.getValue("name").getAsString()).queue();
    }

}
