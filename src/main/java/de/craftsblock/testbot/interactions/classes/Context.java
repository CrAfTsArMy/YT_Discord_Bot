package de.craftsblock.testbot.interactions.classes;

import de.craftsblock.testbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class Context {

    private ContextExecutor executor;
    private CommandDataCollection data;

    public void setExecutor(ContextExecutor executor) {
        this.executor = executor;
    }
    public ContextExecutor getExecutor() {
        return executor;
    }

    public void setData(CommandDataCollection data) {
        this.data = data;
    }
    public CommandDataCollection getData() {
        return data;
    }

    public static class CommandDataCollection {

        private CommandData user;
        private CommandData message;

        public CommandDataCollection setUser(CommandData user) {
            this.user = user;
            return this;
        }

        public CommandDataCollection setMessage(CommandData message) {
            this.message = message;
            return this;
        }

        public CommandData getUser() {
            return user;
        }

        public CommandData getMessage() {
            return message;
        }

        public boolean isUser() {
            return user != null;
        }

        public boolean isMessage() {
            return message != null;
        }

    }

}
