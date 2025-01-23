package com.daxton.unrealhud.listener;

import com.daxton.unrealcore.application.method.SchedulerFunction;
import com.daxton.unrealcore.communication.event.PlayerConnectionSuccessfulEvent;
import com.daxton.unrealhud.UnrealHUD;
import com.daxton.unrealhud.controller.UnrealHUDController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UnrealHUDListener implements Listener {

    @EventHandler//當玩家連線成功
    public void onPlayerJoin(PlayerConnectionSuccessfulEvent event) {
        if(Bukkit.getServer().getPluginManager().getPlugin("UnrealResource") != null){
            Player player = event.getPlayer();
            if(!UnrealHUDController.unrealHUDConfig.isResource_end()){
                SchedulerFunction.runLater(UnrealHUD.unrealCorePlugin.getJavaPlugin(), ()->{
                    UnrealHUDController.sendHUD(player);
                }, 2);
            }
        }else {
            Player player = event.getPlayer();
            SchedulerFunction.runLater(UnrealHUD.unrealCorePlugin.getJavaPlugin(), ()->{
                UnrealHUDController.sendHUD(player);
            }, 2);
        }

    }


    @EventHandler//當玩家登出
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UnrealHUDController.removeHUD(player);
    }

    @EventHandler//當玩家切換世界
    public void onWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        UnrealHUDController.removeHUD(player);
        SchedulerFunction.runLater(UnrealHUD.unrealCorePlugin.getJavaPlugin(), ()->{
            UnrealHUDController.sendHUD(player);
        }, 5);

    }

}
