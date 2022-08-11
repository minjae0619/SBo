package com.mj.sbo.utils;

import com.mj.sbo.Main;

import java.io.File;
import java.util.Map;

public class Properties {

    public static Map<String, String> data;

    static{
        data = Resource.readProperties(new File(Main.PROJECT_PATH + "\\properties.txt"));
    }


}
