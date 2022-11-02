package de.craftsblock.testbot.interactions;

import de.craftsblock.testbot.Main;
import de.craftsblock.testbot.interactions.classes.Button;
import de.craftsblock.testbot.interactions.classes.Command;
import de.craftsblock.testbot.interactions.classes.SelectMenu;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InteractionManager {

    private static final ConcurrentHashMap<String, Command> commands = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, SelectMenu> selectmenus = new ConcurrentHashMap<>();

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

    public static void push() {
        CommandListUpdateAction update = Main.jda().updateCommands();

        for (Map.Entry<String, Command> entry : commands.entrySet())
            update.addCommands(entry.getValue().getData()).queue();

        update.queue();
    }

}
