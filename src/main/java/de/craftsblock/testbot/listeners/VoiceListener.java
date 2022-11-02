package de.craftsblock.testbot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.ConcurrentLinkedQueue;

public class VoiceListener extends ListenerAdapter {

    private final ConcurrentLinkedQueue<Long> tempchannels = new ConcurrentLinkedQueue<>();

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getNewValue() == null)
            leave(event);
        else if (event.getOldValue() == null)
            join(event);
        else {
            leave(event);
            join(event);
        }
    }

    private void leave(GuildVoiceUpdateEvent event) {
        assert event.getOldValue() != null;
        VoiceChannel voiceChannel = (VoiceChannel) event.getOldValue();
        if (tempchannels.contains(voiceChannel.getIdLong()) && voiceChannel.getMembers().size() <= 0)
            voiceChannel.delete().queue();
    }

    private void join(GuildVoiceUpdateEvent event) {
        assert event.getNewValue() != null;
        if (event.getNewValue().getIdLong() == 910048806777815070L) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            Category category = event.getNewValue().getParentCategory();
            VoiceChannel voiceChannel;
            if (category != null)
                voiceChannel = category.createVoiceChannel(" | " + member.getUser().getName()).complete();
            else
                voiceChannel = guild.createVoiceChannel(" | " + member.getUser().getName()).complete();
            guild.moveVoiceMember(member, voiceChannel).queue();
            tempchannels.add(voiceChannel.getIdLong());
        }
    }

}
