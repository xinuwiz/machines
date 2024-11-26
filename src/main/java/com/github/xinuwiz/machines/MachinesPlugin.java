package com.github.xinuwiz.machines;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MachinesPlugin extends JavaPlugin {

    @Getter private static MachinesPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
