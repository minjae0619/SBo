package com.mj.sbo.utils.sound;

import com.mj.sbo.utils.Resource;
import com.mj.sbo.utils.live.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebDriver;

public class Donation {


    public static void play(String text) {
        DriverManager.setProperty();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--headless");
        WebDriver webDriver = new FirefoxDriver(options);
        webDriver.get("https://papago.naver.com/");
        DriverManager.sleep(500);
        webDriver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[1]/div[1]/div/div[3]"))
                .sendKeys("-" + text);
        DriverManager.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[1]/div[1]/div/div[4]/span[1]/span/span/button")).click();
        DriverManager.sleep(10000);
        webDriver.close();

    }
}
