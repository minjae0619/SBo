package com.mj.sbo.utils;


import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class Sound {




    public static void play(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Player player = new Player(fileInputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
