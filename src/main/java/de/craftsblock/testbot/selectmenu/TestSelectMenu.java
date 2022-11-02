package de.craftsblock.testbot.selectmenu;

import de.craftsblock.testbot.interactions.interfaces.SelectMenuExecutor;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

import java.util.Objects;

public class TestSelectMenu implements SelectMenuExecutor {

    @Override
    public void run(SelectMenuInteractionEvent event) {
        assert event.getGuild() != null;
        assert event.getMember() != null;
        for (SelectOption option : event.getSelectMenu().getOptions())
            if (event.getSelectedOptions().contains(option))
                event.getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(option.getValue()))).queue();
            else
                event.getGuild().removeRoleFromMember(event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(option.getValue()))).queue();
        event.reply("Success!").setEphemeral(true).complete().deleteOriginal().queue();
    }

}
