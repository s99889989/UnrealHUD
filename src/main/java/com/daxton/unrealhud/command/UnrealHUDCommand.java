package com.daxton.unrealhud.command;

import com.daxton.unrealhud.UnrealHUD;
import com.daxton.unrealhud.controller.UnrealHUDController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnrealHUDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(!player.isOp()){
                        return true;
                    }
                    player.sendMessage("[UnrealHUD] Reload");
                }
                UnrealHUD.unrealCorePlugin.sendSystemLogger("Reload");
                UnrealHUDController.reload();
                return true;
            }
        }

        return false;
    }

}
