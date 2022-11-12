package de.craftsblock.testbot.listeners;

import de.craftsblock.testbot.interactions.InteractionManager;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InteractionListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        InteractionManager.getCommand(event.getName()).getExecutor().run(event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        InteractionManager.getButton(event.getButton().getId()).getExecutor().run(event);
    }

    @Override
    public void onSelectMenuInteraction(SelectMenuInteractionEvent event) {
        InteractionManager.getSelectMenu(event.getSelectMenu().getId()).getExecutor().run(event);
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        InteractionManager.getContext(event.getName()).getExecutor().run(event);
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        InteractionManager.getContext(event.getName()).getExecutor().run(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        InteractionManager.getModal(event.getModalId()).getExecutor().run(event);
    }
}
