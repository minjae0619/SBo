package com.mj.sbo.utils.live;

import com.mj.sbo.Main;
import com.mj.sbo.objects.Bot;
import com.mj.sbo.utils.sound.Donation;
import com.mj.sbo.utils.sound.Sound;

public class Guide {


    public void sendMessage(String message){
        Bot.sendMessage(message);
    }

    public void sendLog(String log){Main.view.addLog(log);}

    public void playSound(String path){Sound.play(path);}

    public void playDonation(String text){Donation.play(text);}

}
