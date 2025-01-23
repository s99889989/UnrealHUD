package com.daxton.unrealhud.config;

import com.daxton.unrealcore.application.base.YmlFileUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UnrealHUDConfig {

    private boolean enable;

    private boolean resource_end;

    private int refresh_time;

    private String hud;

    private boolean guiHUDEnabled;

    private String guiHUD;


    public UnrealHUDConfig(FileConfiguration fileConfiguration) {
        this.enable = YmlFileUtil.getBoolean(fileConfiguration, "enable", false);
        this.resource_end = YmlFileUtil.getBoolean(fileConfiguration, "resource_end", true);
        this.refresh_time = YmlFileUtil.getInt(fileConfiguration, "refresh_time", 2);
        this.hud = YmlFileUtil.getString(fileConfiguration, "hud", "example");

        this.guiHUDEnabled = YmlFileUtil.getBoolean(fileConfiguration, "gui_hud.enable", false);
        this.guiHUD = YmlFileUtil.getString(fileConfiguration, "gui_hud.hud", "example2");

    }
}
