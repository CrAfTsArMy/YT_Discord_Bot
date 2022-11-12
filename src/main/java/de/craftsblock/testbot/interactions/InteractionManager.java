package de.craftsblock.testbot.interactions;

import de.craftsblock.testbot.Main;
import de.craftsblock.testbot.interactions.classes.*;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InteractionManager {

    private static final ConcurrentHashMap<String, Command> commands = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, SelectMenu> selectmenus = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Context> contexts = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Modal> modals = new ConcurrentHashMap<>();

    public static Command getCommand(String name) {
        if (!commands.containsKey(name))
            commands.put(name, new Command());
        return commands.get(name);
    }

    public static Button getButton(String name) {
        if (!buttons.containsKey(name))
            buttons.put(name, new Button());
        return buttons.get(name);
    }

    public static SelectMenu getSelectMenu(String name) {
        if (!selectmenus.containsKey(name))
            selectmenus.put(name, new SelectMenu());
        return selectmenus.get(name);
    }

    public static Context getContext(String name) {
        if (!contexts.containsKey(name))
            contexts.put(name, new Context());
        return contexts.get(name);
    }

    public static Modal getModal(String name) {
        if (!modals.containsKey(name))
            modals.put(name, new Modal());
        return modals.get(name);
    }

    public static void push() {
        CommandListUpdateAction update = Main.jda().updateCommands();

        for (Map.Entry<String, Command> entry : commands.entrySet())
            update.addCommands(entry.getValue().getData()).queue();

        for (Map.Entry<String, Context> entry : contexts.entrySet()) {
            if (entry.getValue().getData().isMessage())
                update.addCommands(entry.getValue().getData().getMessage()).queue();
            if (entry.getValue().getData().isUser())
                update.addCommands(entry.getValue().getData().getUser()).queue();
        }

        update.queue();
    }

}
