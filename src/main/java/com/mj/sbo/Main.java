package com.mj.sbo;

import com.mj.sbo.event.handler.Listener;
import com.mj.sbo.objects.login.Login;
import com.mj.sbo.storage.Databases;
import com.mj.sbo.ui.MainUI;
import com.mj.sbo.event.handler.Handler;
import com.mj.sbo.listeners.CommandListener;
import com.mj.sbo.utils.Resource;
import com.mj.sbo.utils.update.Update;

import javax.tools.SimpleJavaFileObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Timer;

public class Main {
    public static final String VERSION = "1.0.1";

    public static MainUI view;
    public static Login login;
    public final static String ADMIN = "";
    public final static String PROJECT_PATH = System.getProperty("user.dir");

    static{
        Resource.readProperties(new File(Main.PROJECT_PATH + "\\properties.txt"));
    }

    public static void registerEvents() {
        Handler.addListener(new CommandListener());
    }
    public static void registerEvents(Listener listener) {
        Handler.addListener(listener);
    }
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
//        Update.update();

        registerEvents();
        Resource.createFile();
        Timer timer = new Timer();
        view = new MainUI();
        Databases.getInstance().read();
        view.view();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Resource.readJavaCode();
                timer.cancel();
            }
        }, 1000);
    }




    static Iterable<JavaSourceFromString> getJavaSourceFromString(String code) {
        final JavaSourceFromString jsfs;
        jsfs = new JavaSourceFromString("code", code);
        return new Iterable<JavaSourceFromString>() {
            public Iterator<JavaSourceFromString> iterator() {
                return new Iterator<JavaSourceFromString>() {
                    boolean isNext = true;

                    public boolean hasNext() {
                        return isNext;
                    }

                    public JavaSourceFromString next() {
                        if (!isNext)
                            throw new NoSuchElementException();
                        isNext = false;
                        return jsfs;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}