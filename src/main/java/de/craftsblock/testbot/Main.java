package de.craftsblock.testbot;

import de.craftsblock.craftscore.core.Core;
import de.craftsblock.testbot.interactions.InteractionRegistry;
import de.craftsblock.testbot.listeners.InteractionListener;
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

public class Main extends InteractionRegistry {

    private static JDA jda;

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Core.instance().init();
        Core.instance().enableSQL();
        new SQLManager();
        JDABuilder builder = JDABuilder.create(DoNotOpen.TOKEN, Arrays.asList(GatewayIntent.values()));
        builder.setEnableShutdownHook(true);
        builder.setActivity(Activity.playing("mit euch =D"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda = builder.build();
        jda().awaitReady();
        jda.addEventListener(
                new JoinLeaveListener(),
                new VoiceListener(),
                new InteractionListener()
        );

        register();
    }

    public static JDA jda() {
        return jda;
    }

}
