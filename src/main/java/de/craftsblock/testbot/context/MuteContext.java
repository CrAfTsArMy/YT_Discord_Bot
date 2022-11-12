package de.craftsblock.testbot.context;

import de.craftsblock.testbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MuteContext implements ContextExecutor {

    @Override
    public void run(UserContextInteractionEvent event) {
        mute(Objects.requireNonNull(event.getTargetMember()));
        event.reply("Success").complete().deleteOriginal().queue();
    }

    @Override
    public void run(MessageContextInteractionEvent event) {
        mute(Objects.requireNonNull(event.getTarget().getMember()));
        event.reply("Success").complete().deleteOriginal().queue();
    }

    private void mute(Member member) {
        member.timeoutFor(10, TimeUnit.DAYS).queue();
    }

}
