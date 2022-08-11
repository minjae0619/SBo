package com.mj.sbo.utils.live;

import com.mj.sbo.Main;
import com.mj.sbo.listeners.EventCapture;
import com.mj.sbo.objects.Bot;
import com.mj.sbo.utils.Properties;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class DriverManager {

    private static EventFiringWebDriver eventHandler;
    static {
        setProperty();
    }
    public static void setProperty() {
        InputStream inputStream = Main.class.getResourceAsStream("/driver.exe");
        File file = new File(Main.PROJECT_PATH + "\\driver.exe");
        if (!file.exists())
            try {
                OutputStream fo = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int count = 0;
                while (true) {
                    if ((count = Objects.requireNonNull(inputStream).read(b)) == -1) break;
                    fo.write(b, 0, count);
                }
                inputStream.close();
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.setProperty("webdriver.gecko.driver", file.getPath());
    }


    public static void run(){
        setProperty();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        if(!Main.view.isWebView())
            options.addArguments("--headless");
        Bot.driver = new FirefoxDriver(options);
        eventHandler = new EventFiringWebDriver(Bot.driver);
        eventHandler.register(new EventCapture());
        Bot.driver.manage().window().maximize();
        Bot.driver.get(Bot.url);
        login(Long.parseLong(Properties.data.get("LoginDelay")));
    }

    public static void login(long ms){
        sleep(ms);
        Main.login.login();

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
