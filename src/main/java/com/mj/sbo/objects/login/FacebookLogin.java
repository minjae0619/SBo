package com.mj.sbo.objects.login;

import com.mj.sbo.objects.Bot;
import com.mj.sbo.Main;
import org.openqa.selenium.By;

public class FacebookLogin extends Login{
    @Override
    public void login() {
        try {
            Bot.driver.findElement(By.className("btn-login")).click();
        } catch (Exception e) {
        }
        sleep(1000);
        Bot.driver.findElement(By.className("btn-facebook")).click();
        sleep(1000);
        Bot.driver.switchTo().window((String) Bot.driver.getWindowHandles().toArray()[1]);
        sleep(1000);
        Bot.driver.findElement(By.name("email")).sendKeys(Main.view.id.getText());
        sleep(100);
        Bot.driver.findElement(By.name("pass")).sendKeys(Main.view.password.getText());
        sleep(100);
        Bot.driver.findElement(By.id("loginbutton")).click();
    }
    @Override
    public LoginType getLoginType() {
        return LoginType.FACEBOOK;
    }

}
