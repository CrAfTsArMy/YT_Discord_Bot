package de.craftsblock.testbot.context;

import de.craftsblock.testbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BanContext implements ContextExecutor {

    @Override
    public void run(UserContextInteractionEvent event) {
        ban(Objects.requireNonNull(event.getTargetMember()));
        event.reply("Success").complete().deleteOriginal().queue();
    }

    @Override
    public void run(MessageContextInteractionEvent event) {
        ban(Objects.requireNonNull(event.getTarget().getMember()));
        event.reply("Success").complete().deleteOriginal().queue();
    }

    private void ban(Member member) {
        member.ban(7, TimeUnit.DAYS).queue();
    }

}
