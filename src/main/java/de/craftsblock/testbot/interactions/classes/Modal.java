package de.craftsblock.testbot.interactions.classes;

import de.craftsblock.testbot.interactions.interfaces.ModalExecutor;

public class Modal {

    private ModalExecutor executor;

    public void setExecutor(ModalExecutor executor) {
        this.executor = executor;
    }
    public ModalExecutor getExecutor() {
        return executor;
    }

}
