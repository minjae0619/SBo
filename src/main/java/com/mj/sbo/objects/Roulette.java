package com.mj.sbo.objects;

import java.util.HashMap;
import java.util.Map;

public class Roulette {

    private static Roulette instance;


    public static Roulette getInstance() {
        if(instance == null)
            instance = new Roulette();
        return instance;
    }

    private Map<String, Double> percentage;

    public void add(String message, double percentage){
        this.percentage.put(message, percentage);
    }
    public void remove(String message, double percentage){
        this.percentage.remove(message);
    }


    public String getRandomMessage(){
        double random = (Math.random() * 100);
        double value = 0;
        for(String message : percentage.keySet()){
            value += percentage.get(message);
            if(random <= value)
                return message;
        }
        return "";
    }


    private Roulette(){
        percentage = new HashMap<>();
    }
}
