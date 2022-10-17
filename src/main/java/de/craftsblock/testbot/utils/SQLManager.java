package de.craftsblock.testbot.utils;

import de.craftsarmy.craftscore.core.Core;

public class SQLManager {

    public SQLManager() {
        Core.instance().getSql().bind("localhost", 3306, "discordbot");
        Core.instance().getSql().connect("root", "");
        Core.instance().getSql().update("CREATE TABLE IF NOT EXISTS invites (guild LONG, user LONG, invite VARCHAR(255))");
    }

}
