package de.craftsblock.testbot.context;

import de.craftsblock.testbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageCountContext implements ContextExecutor {

    @Override
    public void run(MessageContextInteractionEvent event) {
        String message = event.getTarget().getContentDisplay();
        event.reply("**Zeichen:** " + message.length() + "\n**Wörter:** " + message.split(" ").length).setEphemeral(true).queue();
    }

}
