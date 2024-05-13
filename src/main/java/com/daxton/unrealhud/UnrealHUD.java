package com.daxton.unrealhud;

import com.daxton.unrealhud.command.UnrealHUDCommand;
import com.daxton.unrealhud.command.UnrealHUDTab;
import com.daxton.unrealhud.controller.UnrealHUDController;
import com.daxton.unrealhud.listener.ResourceListener;
import com.daxton.unrealhud.listener.UnrealHUDListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class UnrealHUD extends JavaPlugin {

    public static UnrealHUD unrealHUD;

    @Override
    public void onEnable() {
        unrealHUD = this;
        Objects.requireNonNull(Bukkit.getPluginCommand("unrealhud")).setExecutor(new UnrealHUDCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("unrealhud")).setTabCompleter(new UnrealHUDTab());

        UnrealHUDController.load();




        if(Bukkit.getServer().getPluginManager().getPlugin("UnrealResource") != null){
            Bukkit.getPluginManager().registerEvents(new ResourceListener(), unrealHUD);
        }else {
            Bukkit.getPluginManager().registerEvents(new UnrealHUDListener(), unrealHUD);
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //發送後臺訊息(系統)
    public static void sendSystemLogger(String message){
        unrealHUD.getLogger().info("System: "+message);
    }

    //發送後臺訊息(錯誤)
    public static void sendErrorLogger(String message){
        unrealHUD.getLogger().info("Error: "+message);
    }

    //發送後臺訊息(一般)
    public static void sendLogger(String message){
        unrealHUD.getLogger().info(message);
    }

    //獲取資源路徑
    public static String getResourceFolder(){
        return unrealHUD.getDataFolder()+"/";
    }

}
