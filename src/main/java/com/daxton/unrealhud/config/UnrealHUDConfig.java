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

    public UnrealHUDConfig(FileConfiguration fileConfiguration) {
        this.enable = YmlFileUtil.getBoolean(fileConfiguration, "enable", true);
        this.resource_end = YmlFileUtil.getBoolean(fileConfiguration, "resource_end", true);
        this.refresh_time = YmlFileUtil.getInt(fileConfiguration, "refresh_time", 2);
        this.hud = YmlFileUtil.getString(fileConfiguration, "hud", "example");
    }
}
