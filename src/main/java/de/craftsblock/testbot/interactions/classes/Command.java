package de.craftsblock.testbot.interactions.classes;

import de.craftsblock.testbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class Command {

    private CommandExecutor executor;
    private CommandData data;

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setData(CommandData data) {
        this.data = data;
    }

    public CommandData getData() {
        return data;
    }

}
