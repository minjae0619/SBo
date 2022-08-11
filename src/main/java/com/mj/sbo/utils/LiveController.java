package com.mj.sbo.utils;

import com.mj.sbo.Main;
import com.mj.sbo.objects.Bot;

public class LiveController {


    public void sendMessage(String message){
        Bot.sendMessage(message);
    }
    public void sendLog(String log){Main.view.addLog(log);}
    public void playSound(String path){Sound.play(path);}


}
