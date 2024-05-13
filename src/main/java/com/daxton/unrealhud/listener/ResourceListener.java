package com.daxton.unrealhud.listener;


import com.daxton.unrealcore.UnrealCore;
import com.daxton.unrealcore.application.method.SchedulerFunction;
import com.daxton.unrealcore.communication.event.PlayerConnectionSuccessfulEvent;
import com.daxton.unrealhud.UnrealHUD;
import com.daxton.unrealhud.controller.UnrealHUDController;
import com.daxton.unrealresource.event.UnrealResourceLoadFinishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ResourceListener implements Listener {


    @EventHandler//當玩家連線成功
    public void onPlayerJoin(PlayerConnectionSuccessfulEvent event) {
        Player player = event.getPlayer();
        if(!UnrealHUDController.unrealHUDConfig.isResource_end()){
            SchedulerFunction.runLater(UnrealHUD.unrealHUD, ()->{
                UnrealHUDController.sendHUD(player);
            }, 2);
        }
    }

    @EventHandler//當玩家資源加載成功
    public void onPlayerJoin(UnrealResourceLoadFinishEvent event) {
        Player player = event.getPlayer();
        if(UnrealHUDController.unrealHUDConfig.isResource_end()){
            SchedulerFunction.runLater(UnrealHUD.unrealHUD, ()->{
                UnrealHUDController.sendHUD(player);
            }, 2);

        }
    }

    @EventHandler//當玩家切換世界
    public void onWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        UnrealHUDController.removeHUD(player);
        SchedulerFunction.runLater(UnrealHUD.unrealHUD, ()->{
            UnrealHUDController.sendHUD(player);
            UnrealCore.sendLogger("切換世界!!");
        }, 5);

    }

}
