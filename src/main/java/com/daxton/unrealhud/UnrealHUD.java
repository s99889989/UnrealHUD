package com.daxton.unrealhud;

import com.daxton.unrealcore.UnrealCorePlugin;
import com.daxton.unrealhud.command.UnrealHUDCommand;
import com.daxton.unrealhud.command.UnrealHUDTab;
import com.daxton.unrealhud.controller.UnrealHUDController;
import com.daxton.unrealhud.listener.ResourceListener;
import com.daxton.unrealhud.listener.UnrealHUDListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class UnrealHUD extends JavaPlugin {

    public static UnrealCorePlugin unrealCorePlugin;

    @Override
    public void onEnable() {
        unrealCorePlugin = new UnrealCorePlugin(this);
        Objects.requireNonNull(Bukkit.getPluginCommand("unrealhud")).setExecutor(new UnrealHUDCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("unrealhud")).setTabCompleter(new UnrealHUDTab());

        UnrealHUDController.load();

        Bukkit.getPluginManager().registerEvents(new UnrealHUDListener(), this);
        if(Bukkit.getServer().getPluginManager().getPlugin("UnrealResource") != null){
            Bukkit.getPluginManager().registerEvents(new ResourceListener(), this);
        }


    }

    @Override
    public void onDisable() {

    }

}
