package com.daxton.unrealhud;


import com.daxton.unrealhud.application.CustomValueConvert;
import com.daxton.unrealhud.controller.UnrealHUDController;


public class HUDFunction {

    public String placeholder(String content){
        if(content.startsWith("{") && content.endsWith("}") && content.length() > 30){
            content = content.replace("{", "<[").replace("}", "]>");
            return CustomValueConvert.valueNBT(content, UnrealHUDController.customValue);
        }
        return CustomValueConvert.value(content, UnrealHUDController.customValue);
    }

    public void placeholder(){
        UnrealHUDController.hudDataConfigList.forEach(moduleData->{
            moduleData.applyFunctionToFields(this::placeholder);
        });
        UnrealHUDController.gui_hudDataConfigList.forEach(moduleData->{
            moduleData.applyFunctionToFields(this::placeholder);
        });
    }

}
