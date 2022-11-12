package de.craftsblock.testbot.interactions;

import de.craftsblock.testbot.buttons.TestButton;
import de.craftsblock.testbot.buttons.TicketCreateButton;
import de.craftsblock.testbot.commands.GreetCommand;
import de.craftsblock.testbot.commands.TestCommand;
import de.craftsblock.testbot.commands.TicketCommand;
import de.craftsblock.testbot.context.BanContext;
import de.craftsblock.testbot.context.KickContext;
import de.craftsblock.testbot.context.MessageCountContext;
import de.craftsblock.testbot.context.MuteContext;
import de.craftsblock.testbot.interactions.classes.Context;
import de.craftsblock.testbot.modals.GreetModal;
import de.craftsblock.testbot.selectmenu.TestSelectMenu;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InteractionRegistry extends InteractionManager {

    public static void register() {
        registerCommands();
        registerButtons();
        registerSelectMenus();
        registerContexts();
        registerModals();
        push();
    }

    private static void registerCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("test").setData(Commands.slash("test", "Test Command for testing SlashCommands").setGuildOnly(true));

        getCommand("greet").setExecutor(new GreetCommand());
        getCommand("greet").setData(Commands.slash("greet", "Grüße an einen Namen").setGuildOnly(true));

        getCommand("ticket").setExecutor(new TicketCommand());
        getCommand("ticket").setData(Commands.slash("ticket", "Erstelle einen Ticket Channel"));
    }

    private static void registerButtons() {
        getButton("random").setExecutor(new TestButton());
        getButton("ticket_create").setExecutor(new TicketCreateButton());
    }

    private static void registerSelectMenus() {
        getSelectMenu("role").setExecutor(new TestSelectMenu());
    }

    private static void registerContexts() {
        getContext("Nutzer Muten").setExecutor(new MuteContext());
        getContext("Nutzer Muten").setData(
                new Context.CommandDataCollection()
                        .setUser(Commands.user("Nutzer Muten"))
                        .setMessage(Commands.message("Nutzer Muten"))
        );

        getContext("Nutzer Kicken").setExecutor(new KickContext());
        getContext("Nutzer Kicken").setData(
                new Context.CommandDataCollection()
                        .setUser(Commands.user("Nutzer Kicken"))
                        .setMessage(Commands.message("Nutzer Kicken"))
        );

        getContext("Nutzer Bannen").setExecutor(new BanContext());
        getContext("Nutzer Bannen").setData(
                new Context.CommandDataCollection()
                        .setUser(Commands.user("Nutzer Bannen"))
                        .setMessage(Commands.message("Nutzer Bannen"))
        );

        getContext("Wörter Zählen").setExecutor(new MessageCountContext());
        getContext("Wörter Zählen").setData(
                new Context.CommandDataCollection()
                        .setMessage(Commands.message("Wörter Zählen"))
        );
    }

    private static void registerModals() {
        getModal("greet").setExecutor(new GreetModal());
    }

}
