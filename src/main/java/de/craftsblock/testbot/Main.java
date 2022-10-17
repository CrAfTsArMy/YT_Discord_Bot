package de.craftsblock.testbot;

import de.craftsarmy.craftscore.core.Core;
import de.craftsblock.testbot.listeners.JoinLeaveListener;
import de.craftsblock.testbot.listeners.VoiceListener;
import de.craftsblock.testbot.secret.DoNotOpen;
import de.craftsblock.testbot.utils.SQLManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Arrays;

public class Main {

    private static JDA jda;

    public static void main(String[] args) {
        Core.instance().init();
        new SQLManager();
        JDABuilder builder = JDABuilder.create(DoNotOpen.TOKEN, Arrays.asList(GatewayIntent.values()));
        builder.addEventListeners(new VoiceListener());
        builder.setEnableShutdownHook(true);
        builder.setActivity(Activity.playing("mit euch =D"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda = builder.build();
        jda.addEventListener(new JoinLeaveListener());

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            jda.getPresence().setStatus(OnlineStatus.OFFLINE);
//            jda.shutdown();
//        }));
    }

    public static JDA jda() {
        return jda;
    }

}
