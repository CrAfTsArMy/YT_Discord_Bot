package de.craftsblock.testbot.interactions.classes;

import de.craftsblock.testbot.interactions.interfaces.ButtonExecutor;

public class Button {

    private ButtonExecutor executor;

    public void setExecutor(ButtonExecutor executor) {
        this.executor = executor;
    }
    public ButtonExecutor getExecutor() {
        return executor;
    }

}
