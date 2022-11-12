package de.craftsblock.testbot.buttons;

import de.craftsblock.testbot.interactions.interfaces.ButtonExecutor;
import de.craftsblock.testbot.secret.DoNotOpen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TicketCreateButton implements ButtonExecutor {

    @Override
    public void run(ButtonInteractionEvent event) {
        event.deferReply(true).complete().deleteOriginal().queue();

        Guild guild = event.getGuild();
        assert guild != null;

        Category cat = guild.getCategoryById(808593306266370078L);
        assert cat != null;

        TextChannel channel = cat.createTextChannel(Objects.requireNonNull(event.getMember()).getEffectiveName()).complete();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xf48363)
                .setTimestamp(OffsetDateTime.now())
                .setFooter("© CraftsBlock", DoNotOpen.icon())
                .setTitle("Willkommen im Ticket Support")
                .setDescription("Ein Supporter wird sich demnächst um deine Anfrage kümmern, bitte habe etwas gedult!")
                .setThumbnail(DoNotOpen.icon());

        channel.upsertPermissionOverride(guild.getPublicRole())
                .setDenied(Permission.values()).queue();

        channel.upsertPermissionOverride(event.getMember())
                .setDenied(Permission.values())
                .setAllowed(
                        Permission.MESSAGE_SEND,
                        Permission.VIEW_CHANNEL,
                        Permission.MESSAGE_HISTORY,
                        Permission.MESSAGE_ATTACH_FILES,
                        Permission.MESSAGE_ADD_REACTION,
                        Permission.MESSAGE_EMBED_LINKS
                ).queue();

        channel.upsertPermissionOverride(Objects.requireNonNull(guild.getRoleById(1040978213792399400L)))
                .setDenied(Permission.values())
                .setAllowed(
                        Permission.MESSAGE_SEND,
                        Permission.VIEW_CHANNEL,
                        Permission.MESSAGE_HISTORY,
                        Permission.MESSAGE_ATTACH_FILES,
                        Permission.MESSAGE_ADD_REACTION,
                        Permission.MESSAGE_EMBED_LINKS,
                        Permission.MESSAGE_MANAGE
                ).queue();


        channel.sendMessage(event.getMember().getAsMention())
                .addEmbeds(builder.build())
                .addActionRow(
                        Button.danger("ticket_close", "Ticket Schließen")
                                .withEmoji(Emoji.fromUnicode("🔐"))
                ).queue();
    }

}
