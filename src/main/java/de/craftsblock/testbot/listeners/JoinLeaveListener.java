package de.craftsblock.testbot.listeners;

import de.craftsblock.craftscore.core.Core;
import de.craftsblock.testbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class JoinLeaveListener extends ListenerAdapter {

    private final ConcurrentHashMap<Invite, Integer> usages = new ConcurrentHashMap<>();

    public JoinLeaveListener() {
        new Thread(() -> {
            try {
                Main.jda().awaitReady();
                for (Guild guild : Main.jda().getGuilds())
                    for (Invite invite : guild.retrieveInvites().complete())
                        usages.put(invite, invite.getUses());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }).start();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        TextChannel textChannel = Objects.requireNonNull(guild.getDefaultChannel()).asTextChannel();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription(member.getAsMention() + " hat unseren Super tollen Discord Server betreten. Wir sind nun **" + guild.getMembers().size() + "** Mitglieder!");
        builder.setTimestamp(OffsetDateTime.now());
        builder.setColor(0x00b16a);
        if (member.getUser().getAvatarUrl() != null)
            builder.setThumbnail(member.getUser().getAvatarUrl());
        else
            builder.setThumbnail(member.getUser().getDefaultAvatarUrl());

        Invite used = null;
        for (Invite i : guild.retrieveInvites().complete())
            if (!usages.containsKey(i)) {
                used = i;
                usages.put(i, i.getUses());
                break;
            } else if (usages.get(i) < i.getUses()) {
                used = i;
                usages.put(i, i.getUses());
                break;
            }

        if (used != null) {
            try {
                PreparedStatement statement = Core.instance().getSql().prepareStatement("INSERT INTO invites (guild, user, invite) VALUES (?, ?, ?)");
                statement.setLong(1, guild.getIdLong());
                statement.setLong(2, member.getIdLong());
                statement.setString(3, used.getUrl());
                Core.instance().getSql().update(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            builder.setFooter("Eingeladen von " + used.getInviter().getName(), used.getInviter().getAvatarUrl());
        }

        textChannel.sendMessageEmbeds(builder.build()).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        TextChannel textChannel = Objects.requireNonNull(guild.getDefaultChannel()).asTextChannel();
        EmbedBuilder builder = new EmbedBuilder();

        builder.setDescription(user.getName() + "#" + user.getDiscriminator() + " hat uns leider verlassen. Wir sind nun **" + guild.getMembers().size() + "** Mitglieder!");
        builder.setTimestamp(OffsetDateTime.now());
        builder.setColor(0xfe7968);

        if (user.getAvatarUrl() != null)
            builder.setThumbnail(user.getAvatarUrl());
        else
            builder.setThumbnail(user.getDefaultAvatarUrl());

        try {
            PreparedStatement request = Core.instance().getSql().prepareStatement("SELECT * FROM invites WHERE guild=? AND user=?");
            request.setLong(1, guild.getIdLong());
            request.setLong(2, user.getIdLong());
            ResultSet result = Core.instance().getSql().query(request);
            if (result.next()) {
                String url = result.getString("invite");
                AtomicReference<Invite> invite = new AtomicReference<>();
                guild.retrieveInvites().complete().forEach(i -> {
                    if (i.getUrl().equals(url))
                        invite.set(i);
                });
                if (invite.get() != null)
                    builder.setFooter("Eingeladen von " + invite.get().getInviter().getName(), invite.get().getInviter().getAvatarUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        textChannel.sendMessageEmbeds(builder.build()).queue();

        try {
            PreparedStatement remove = Core.instance().getSql().prepareStatement("DELETE FROM invites WHERE guild=? AND user=?");
            remove.setLong(1, guild.getIdLong());
            remove.setLong(2, user.getIdLong());
            Core.instance().getSql().update(remove);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildInviteCreate(GuildInviteCreateEvent event) {
        usages.put(event.getInvite(), event.getInvite().getUses());
    }

}
