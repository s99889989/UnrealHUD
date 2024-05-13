package com.daxton.unrealhud.controller;

import com.daxton.unrealcore.application.UnrealCoreAPI;
import com.daxton.unrealcore.application.base.PluginUtil;
import com.daxton.unrealcore.application.method.SchedulerFunction;
import com.daxton.unrealcore.display.been.module.ModuleData;
import com.daxton.unrealhud.HUDFunction;
import com.daxton.unrealhud.UnrealHUD;
import com.daxton.unrealhud.config.UnrealHUDConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnrealHUDController {

    //設定檔
    public static UnrealHUDConfig unrealHUDConfig;

    public static SchedulerFunction.RunTask runTask;

    //佔位符列表
    public static Map<String, String> customValue = new ConcurrentHashMap<>();

    public static String contentString = "null";
    public static List<String> keyList = new ArrayList<>();

    public static List<ModuleData> hudDataConfigList;
    public static void load(){
        if(Bukkit.getPluginManager().getPlugin("UnrealCore") == null){
            return;
        }
        //建立設定檔
        PluginUtil.CreateConfig(UnrealHUD.unrealHUD);

        FileConfiguration hudConfig = getYmlFile("config.yml");
        unrealHUDConfig = new UnrealHUDConfig(hudConfig);


        FileConfiguration hud = getYmlFile("hud/"+unrealHUDConfig.getHud()+".yml");
        hudDataConfigList = UnrealCoreAPI.getHUDList("", hud);
        HUDFunction hudFunction =new HUDFunction();


        if(Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null){
            boolean enable = unrealHUDConfig.isEnable();
            if(enable){
                hudFunction.placeholder();
                contentString = "null";
                keyList = new ArrayList<>();
                customValue.forEach((content, contentChange) -> {
                    keyList.add(contentChange);
                    contentString += " . %"+content+"%";
                });

                updatePlaceholder();
            }

        }


    }
    //重新讀取設定
    public static void reload(){
        Bukkit.getOnlinePlayers().forEach(UnrealHUDController::removeHUD);
        customValue.clear();
        runTask.cancel();
        load();
        Bukkit.getOnlinePlayers().forEach(UnrealHUDController::sendHUD);

    }

    //更新自訂佔位符
    public static void updatePlaceholder(){
        int refresh_time = unrealHUDConfig.getRefresh_time();
        runTask = SchedulerFunction.runTimer(UnrealHUD.unrealHUD,()->{
            Bukkit.getOnlinePlayers().forEach(player -> {
                String contentStringPlaceholder = PlaceholderAPI.setPlaceholders(player, contentString);

                String[] strings = contentStringPlaceholder.split(" . ");
                Map<String, String> customValueMap = new HashMap<>();
                for(int i = 1; i < strings.length ; i++){
                    String contentChange = keyList.get(i-1);
                    String value = strings[i];
                    customValueMap.put(contentChange, value);
                }
                UnrealCoreAPI.customValueMultiSet(player, customValueMap);
            });


        }, 0, refresh_time);

    }
    //            contentString = "null";
//            customValue.forEach((content, contentChange) -> {
//                contentString += " . %"+content+"%";
////                Bukkit.getOnlinePlayers().forEach(player -> {
////                    String value = PlaceholderAPI.setPlaceholders(player, "%"+content+"%");
////                    UnrealCoreAPI.setCustomValue(player, contentChange, value);
////                });
//            });

    //向玩家發送HUD
    public static void sendHUD(Player player){
        boolean enable = unrealHUDConfig.isEnable();
        if(!enable){
            return;
        }
        UnrealCoreAPI.setHUD(player, UnrealHUDController.hudDataConfigList);
    }
    public static void removeHUD(Player player){
        UnrealCoreAPI.removeHUD(player, UnrealHUDController.hudDataConfigList);
    }


    //從插件預設路徑獲取YML檔案
    public static FileConfiguration getYmlFile(String path){
        File file = new File(UnrealHUD.getResourceFolder(), path);
        return YamlConfiguration.loadConfiguration(file);
    }
}
