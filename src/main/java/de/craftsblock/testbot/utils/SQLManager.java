package de.craftsblock.testbot.utils;

import de.craftsblock.craftscore.core.Core;
import de.craftsblock.testbot.secret.DoNotOpen;

public class SQLManager {

    public SQLManager() {
        Core.instance().getSql().bind("localhost", 3306, "discordbot");
        DoNotOpen.mysql$login();
        Core.instance().getSql().update("CREATE TABLE IF NOT EXISTS invites (guild LONG, user LONG, invite VARCHAR(255))");
    }

}
