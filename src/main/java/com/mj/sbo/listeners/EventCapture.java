package com.mj.sbo.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
//WebDriver Event Listeners
public class EventCapture implements WebDriverEventListener{


    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
        System.out.println("In afterChangeValueOf");
    }

    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {
        System.out.println(1);

    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        System.out.println(2);

    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {
        System.out.println(3);

    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {
        System.out.println(4);

    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        System.out.println(5);

    }

    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
        System.out.println(6);

    }

    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        System.out.println(7);

    }

    @Override
    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        System.out.println(8);

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {
        System.out.println(9);

    }

    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        System.out.println(10);

    }

    @Override
    public void beforeNavigateRefresh(WebDriver arg0) {

        System.out.println(11);
    }

    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {
        System.out.println(12);

    }

    @Override
    public void onException(Throwable arg0, WebDriver arg1) {
        System.out.println(13);

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        System.out.println(14);

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        System.out.println(15);
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        System.out.println(16);
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        System.out.println(17);
    }

    @Override
    public void afterAlertAccept(WebDriver arg0) {
// TODO Auto-generated method stub
        System.out.println(18);
    }

    @Override
    public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
// TODO Auto-generated method stub
        System.out.println(19);
    }

    @Override
    public void beforeAlertAccept(WebDriver arg0) {
// TODO Auto-generated method stub
        System.out.println(20);
    }

    @Override
    public void beforeAlertDismiss(WebDriver arg0) {
// TODO Auto-generated method stub
        System.out.println(21);
    }

    @Override
    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
// TODO Auto-generated method stub
        System.out.println(22);
    }

    @Override
    public void afterAlertDismiss(WebDriver arg0) {
// TODO Auto-generated method stub
        System.out.println(23);
    }

    @Override
    public void beforeScript(String arg0, WebDriver arg1) {
// TODO Auto-generated method stub
        System.out.println(24);
    }

    @Override
    public void afterNavigateRefresh(WebDriver arg0) {
// TODO Auto-generated method stub
        System.out.println(25);
    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {
// TODO Auto-generated method stub
        System.out.println(26);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        System.out.println(27);
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        System.out.println(28);
    }

}