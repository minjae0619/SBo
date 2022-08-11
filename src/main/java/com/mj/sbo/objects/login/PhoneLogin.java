package com.mj.sbo.objects.login;

import com.mj.sbo.objects.Bot;
import com.mj.sbo.Main;
import org.openqa.selenium.By;

public class PhoneLogin extends Login{
    @Override
    public void login() {
        sleep(1000);
        try {
            Bot.driver.findElement(By.className("btn-login")).click();
        } catch (Exception e) {
        }
        sleep(500);
        Bot.driver.findElement(By.cssSelector(
                        "body > div.ReactModalPortal > div > div > div > div.modal-body > div > div.input-wrap > ul > li:nth-child(3)"))
                .click();
        sleep(50);
        Bot.driver.findElement(By.cssSelector(
                        "body > div.ReactModalPortal > div > div > div > div.modal-body > div > div > ul > li:nth-child(1) > div > div > input"))
                .sendKeys(Main.view.id.getText());
        sleep(50);
        Bot.driver.findElement(By.cssSelector(
                        "body > div.ReactModalPortal > div > div > div > div.modal-body > div > div > ul > li:nth-child(2) > div > div > input"))
                .sendKeys(Main.view.password.getText());
        sleep(50);
        Bot.driver.findElement(By.cssSelector(
                        "body > div.ReactModalPortal > div > div > div > div.modal-body > div > div > div.btn-submit > button"))
                .click();
    }
    @Override
    public LoginType getLoginType() {
        return LoginType.PHONE;
    }

}
