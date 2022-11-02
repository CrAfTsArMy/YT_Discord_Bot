package de.craftsblock.testbot.interactions.classes;

import de.craftsblock.testbot.interactions.interfaces.SelectMenuExecutor;

public class SelectMenu {

    private SelectMenuExecutor executor;

    public void setExecutor(SelectMenuExecutor executor) {
        this.executor = executor;
    }
    public SelectMenuExecutor getExecutor() {
        return executor;
    }

}
