package com.mj.sbo.objects.login;

import com.mj.sbo.objects.Bot;
import com.mj.sbo.Main;
import org.openqa.selenium.By;

public class AppleLogin extends Login{
    @Override
    public void login() {
        sleep(2000);
        try {
            Bot.driver.findElement(By.className("btn-login")).click();
        } catch (Exception e) {
        }
        sleep(2000);
        Bot.driver.findElement(By.className("btn-apple")).click();
        Bot.driver.switchTo().window((String) Bot.driver.getWindowHandles().toArray()[1]);
        Bot.driver.findElement(By.id("account_name_text_field")).sendKeys(Main.view.id.getText());
        sleep(100);
        Bot.driver.findElement(By.id("sign-in")).click();
        sleep(1000);
        Bot.driver.findElement(By.id("password_text_field")).sendKeys(Main.view.password.getText());
        sleep(100);
        Bot.driver.findElement(By.id("sign-in")).click();
        sleep(1000);
        Bot.driver.findElement(By.cssSelector(
                        "#\\31 630020010742-0 > div > div > button.button.button-primary.last.nav-action.pull-right.weight-medium"))
                .click();
    }
    @Override
    public LoginType getLoginType() {
        return LoginType.APPLE;
    }

}
