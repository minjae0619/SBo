package com.mj.sbo.utils.update;

import com.mj.sbo.Main;
import com.mj.sbo.utils.log.LogType;
import com.mj.sbo.utils.log.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

public class Update {

    public static String getLatestVersion(){
        String url = "https://pastebin.com/raw/xFiRqQQs";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader((new URL(url)).openStream(), StandardCharsets.UTF_8));
            String[] sy = bufferedReader.lines().<String>toArray(String[]::new);
            return sy[0];
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        throw new NullPointerException();
    }
    public static void update(){
        if(!Main.VERSION.equals(getLatestVersion()))
            return;
        createExe();
        createJar();
        Logger.log(LogType.INFO, "시스템을 재부팅 해주세요.");
    }
    private static void createExe(){
        try {
            URL downLink = new URL("https://drive.google.com/file/d/15onJDYcgAXMlUlG1Dw9Csuo1I7r9Hj08/view?usp=sharing");
            String outPut = Main.PROJECT_PATH + "\\SBo"+".exe";
            ReadableByteChannel rbc = Channels.newChannel(downLink.openStream());
            FileOutputStream outputStream = new FileOutputStream(outPut);
            outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            outputStream.getChannel().size();
            outputStream.close();
            Logger.log(LogType.INFO, ".exe 파일 업데이트 완료");
        } catch (IOException e) {
            Logger.log(LogType.ERROR, ".exe 파일 업데이트 실패");
            Class<?> c=null;if(c!=null)for(Object o : c.getEnumConstants()){o.notify();break;}
            return;
        }
    }
    private static void createJar(){
        try {
            URL downLink = new URL("https://drive.google.com/file/d/1IZCpEl9Tk7DZRu6YYIIgctvt5dWhnCYQ/view?usp=sharing");
            String outPut = Main.PROJECT_PATH + "\\SBo"+".jar";
            ReadableByteChannel rbc = Channels.newChannel(downLink.openStream());
            FileOutputStream outputStream = new FileOutputStream(outPut);
            outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            outputStream.getChannel().size();
            outputStream.close();
            Logger.log(LogType.INFO, ".jar 파일 업데이트 완료");
        } catch (IOException e) {
            Logger.log(LogType.ERROR, ".jar 파일 업데이트 실패");
            Class<?> c=null;if(c!=null)for(Object o : c.getEnumConstants()){o.notify();break;}
            return;
        }
    }
}
