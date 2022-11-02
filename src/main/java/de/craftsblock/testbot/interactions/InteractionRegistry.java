package de.craftsblock.testbot.interactions;

import de.craftsblock.testbot.buttons.TestButton;
import de.craftsblock.testbot.commands.TestCommand;
import de.craftsblock.testbot.selectmenu.TestSelectMenu;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InteractionRegistry extends InteractionManager {

    public static void register() {
        registerCommands();
        registerButtons();
        registerSelectMenus();
        push();
    }

    private static void registerCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("test").setData(Commands.slash("test", "Test Command for testing SlashCommands").setGuildOnly(true));
    }

    private static void registerButtons() {
        getButton("random").setExecutor(new TestButton());
    }

    private static void registerSelectMenus() {
        getSelectMenu("role").setExecutor(new TestSelectMenu());
    }

}
