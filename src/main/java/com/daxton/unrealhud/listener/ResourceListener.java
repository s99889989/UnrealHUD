package com.daxton.unrealhud.listener;



import com.daxton.unrealcore.application.method.SchedulerFunction;

import com.daxton.unrealhud.UnrealHUD;
import com.daxton.unrealhud.controller.UnrealHUDController;
import com.daxton.unrealresource.event.UnrealResourceLoadFinishEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ResourceListener implements Listener {

    @EventHandler//當玩家資源加載成功
    public void onPlayerJoin(UnrealResourceLoadFinishEvent event) {
        Player player = event.getPlayer();
        if(UnrealHUDController.unrealHUDConfig.isResource_end()){
            SchedulerFunction.runLater(UnrealHUD.unrealCorePlugin.getJavaPlugin(), ()->{
                UnrealHUDController.sendHUD(player);
            }, 2);

        }
    }


}
