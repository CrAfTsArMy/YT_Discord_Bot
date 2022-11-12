package de.craftsblock.testbot.commands;

import de.craftsblock.testbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.OffsetDateTime;

public class TicketCommand implements CommandExecutor {

    @Override
    public void run(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setFooter("© CraftsBlock");
        builder.setColor(0x5af7c1);
        builder.setTimestamp(OffsetDateTime.now());
        builder.setTitle("Ticket Support");
        builder.setDescription("Öffne ein neues Ticket!");
        event.replyEmbeds(
                builder.build()
        ).addActionRow(
                Button.success("ticket_create", "Erstelle ein Ticket")
                        .withEmoji(Emoji.fromUnicode("➕"))
        ).queue();
    }

}
