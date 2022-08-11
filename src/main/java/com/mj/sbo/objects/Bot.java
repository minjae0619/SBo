package com.mj.sbo.objects;

import java.util.*;

import com.mj.sbo.Main;
import com.mj.sbo.event.*;
import com.mj.sbo.event.handler.Handler;
import com.mj.sbo.objects.live.ElementId;
import com.mj.sbo.utils.Properties;
import com.mj.sbo.utils.Resource;
import com.mj.sbo.utils.live.DriverManager;
import com.mj.sbo.utils.log.LogType;
import com.mj.sbo.utils.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Bot {


    public static String developerName = "R";
    public static WebDriver driver = null;
    public static int max = 2;
    public static Map<String, String> commands;
    public static int userSize;
    public static String url;
    public static String joinMessage;
    public static String lastMessage;
    public static int lastSpoonElement;
    public static Timer timer;
    public static List<String> users;
    public static List<String> playList;
    public static String spoonMessage;
    public static int spoon;
    public static String lastLikeUser;


    public static void run() {
        DriverManager.run();
        sleep(1000);
        try {
            timer = new Timer();
            int i = 0;
            i++;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    check(); // command , message
                    userSize();
                    spoonCheck();
                    likeCheck();
                }
            }, 0, 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
        userSize = getUserSize();
        return;
    }

    public static void userSize() {
        int size = Bot.getUserSize();
        if (Bot.userSize < size) {
            Bot.reloadUsers();
        } else if (Bot.userSize > size) {
            Bot.reloadUsers();
        }
        if (size != 0)
            Bot.userSize = size;

    }

    public static void reloadUsers() {
//

//        if (users == null)
//            users = new ArrayList<String>();
//        try {
//            driver.findElement(By.cssSelector("#root > div > main > div > div > div.live-side-contents > div > div.common-list-wrap.user-list-wrap.on > div.list-header > button")).click();
//        } catch (Exception e) {
//            try {
//                driver.findElement(By.cssSelector(
//                                "#root > div > main > div > div > div.live-side-contents > div > div.btn-tab-wrap > ul > li:nth-child(3) > button"))
//                        .click();
//                sleep(500);
//                driver.findElement(By.cssSelector("#root > div > main > div > div > div.live-side-contents > div > div.common-list-wrap.user-list-wrap.on > div.list-header > button")).click();
//            } catch (Exception e2) {
//                // TODO: handle exception
//            }
//        }
//        List<String> newUsers = getUsers();
//        if (newUsers.size() <= 1)
//            return;
//        if (users.size() != newUsers.size()) {
//            List<String> exitUsers = new ArrayList<String>();
//            List<String> joinUsers = new ArrayList<String>();
//            for (String name : users) {
//                if (!newUsers.contains(name)) {
//                    exitUsers.add(name);
//                }
//            }
//            for (String name : newUsers) {
//                if (!users.contains(name)) {
//                    joinUsers.add(name);
//                }
//            }
//            if (!users.isEmpty()) {
//                for (String name : joinUsers) {
    //                    JoinEvent event = new JoinEvent(name, "");
    //                    Handler.callEvent(event);
    //                    Logger.log(LogType.JOIN,name + "님이 입장하였습니다.");
//
//                }
//                for (String name : exitUsers) {
//                    QuitEvent event = new QuitEvent(name, "");
//                    Handler.callEvent(event);
//                    Logger.log(LogType.QUIT, name + "님이 퇴장하였습니다.");
//                }
//            }
//        }
//        users = newUsers;
    }

    public static String getTime() {
        try {
            return driver.findElement(By.xpath(
                    "/html/body/div[1]/div/main/div/div/div[1]/div/div[1]/div[2]/div/div/button")).getText();///pre"));///div/div/div/div"));///pre"));
        } catch (Exception e) {
            try {
                List<WebElement> list = driver.findElements(By.xpath(
                        "/html/body/div[1]/div/main/div/div/div[1]/div/div[1]/div[2]/div/div/button"));///pre"));///div/div/div/div"));///pre"));
                return list.get(list.size() - 1).getText();
            } catch (Exception e2) {
                return "null";
            }
        }
    }

    public static List<String> getUsers() {
        List<String> users = new ArrayList<String>();
        int a = 1;
        List<WebElement> we = driver.findElements(By.xpath(
                "/html/body/div[1]/div/main/div/div/div[2]/div/div[3]/div[2]/div[1]/ul[1]/li[*]/div/div[1]"));
        while (a <= userSize + 1) {
            try {
                users.add(we.get(a - 1).getText());

            } catch (Exception e) {
            }
            a++;
        }
        return users;
    }

    public static void stop() {
        if (driver != null) {
            driver.close();
            timer.cancel();
            //	driver.close();
            driver = null;
            commands = null;
            joinMessage = null;
        }
    }

    public static String getXpath(WebElement element) {
        int n = element.findElements(By.xpath("./ancestor::*")).size();
        String path = "";
        WebElement current = element;
        for (int i = n; i > 0; i--) {
            String tag = current.getTagName();
            int lvl = current.findElements(By.xpath("./preceding-sibling::" + tag)).size() + 1;
            path = String.format("/%s[%d]%s", tag, lvl, path);
            current = current.findElement(By.xpath("./parent::*"));
        }
        return "/" + current.getTagName() + path;
    }


    public static int getUserSize() {
        try {
            return Integer.parseInt(driver.findElement(By.cssSelector(
                            "#root > div > main > div > div > div.live-detail-contents > div > div.live-detail-info-container.listener > div.live-info > div > ul > li:nth-child(3) > button"))
                    .getText());
        } catch (Exception e) {
            max = 2;
            return 0;
        }
    }

    public static boolean spoonCheck() {
        try {
            List<WebElement> list = driver.findElements(By.xpath(
                    "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div[1]/div/div[1]/ul/li[*]/div/div/div/div[1]/div/pre"));
            if (list.size() == lastSpoonElement)
                return false;
            String text = list.get(list.size() - 1).getText().replace("스푼", "");
            int value = 0;
            if (text.contains("X")) {
                String[] arr = text.split(" X ");
                value = Integer.parseInt(arr[0]);
                int sum = Integer.parseInt(arr[1]);
                value = (value * sum);
            } else {
                value = Integer.parseInt(text);
            }
            spoon += value;
            lastSpoonElement = list.size();
            if (lastSpoonElement == 99)
                driver.navigate().refresh();
            SpoonEvent event = new SpoonEvent(getName(), value, "");
            Handler.callEvent(event);
            sendMessage(event.getSpoonMessage());
            return true;
        } catch (Exception e) {
               try {
                List<WebElement> list = driver.findElements(By.xpath(
                        "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[*]/div/div/div/div[1]/div/pre"));
                if (list.size() == lastSpoonElement)
                    return false;
                String text = list.get(list.size() - 1).getText().replace("스푼", "");
                int value = 0;
                if (text.contains("X")) {
                    String[] arr = text.split(" X ");
                    value = Integer.parseInt(arr[0]);
                    int sum = Integer.parseInt(arr[1]);
                    value = (value * sum);
                } else {
                    value = Integer.parseInt(text);
                }
                spoon += value;
                lastSpoonElement = list.size();
                SpoonEvent event = new SpoonEvent(getName(), value, "");
                Handler.callEvent(event);
                sendMessage(event.getSpoonMessage());
                return true;
            } catch (Exception e2) {
            }
            return false;
        }
    }

    public static void likeCheck() {
        max++;
        String name = getLikeUser();
        if (name.equals("") || name.equals(lastLikeUser))
            return;
        lastLikeUser = name;
        Logger.log(LogType.LIKE, name + "님이 좋아요를 누르셨습니다.");
        LikeEvent likeEvent = new LikeEvent(name);
        Handler.callEvent(likeEvent);

    }

    public static void check() {
        max++;
        String text = getText();
        String name = getName();
        if (text.equals(lastMessage))
            return;
        if(text.contains("님이 입장하였습니다.")) {
            name = text.replace("님이 입장하였습니다.", "");
            JoinEvent event = new JoinEvent(name, "");
            Handler.callEvent(event);
            Logger.log(LogType.JOIN, name + "님이 입장하였습니다.");
        }else if (!text.startsWith("!")) {
            ChatEvent chatEvent = new ChatEvent(name, text);
            Handler.callEvent(chatEvent);
            Logger.log(LogType.CHAT, name + " : " + text);
        }else if (text.startsWith("!")) {
            CommandEvent commandEvent = new CommandEvent(name, text);
            Handler.callEvent(commandEvent);
            Logger.log(LogType.COMMAND, name + " : " + text);
        }
        lastMessage = text;

    }

    public static boolean isManager() {
        try {
            List<WebElement> list = driver.findElements(By.xpath(
                    "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div[1]/div/div[1]/ul/li[*]/div/div/div/p/button"));
            String xpath = getXpath(list.get(list.size() - 1)).replaceAll("button", "span");
            String text = driver.findElement(By.xpath(xpath)).getText();
            return text.equalsIgnoreCase("dj") || text.equalsIgnoreCase("매니저");
        } catch (Exception e) {
            try {
                List<WebElement> list = driver.findElements(By.xpath(
                        "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[*]/div/div/div/p/button"));
                String xpath = getXpath(list.get(list.size() - 1)).replaceAll("button", "span");
                String text = driver.findElement(By.xpath(xpath)).getText();
                return text.equalsIgnoreCase("dj") || text.equalsIgnoreCase("매니저");
            } catch (Exception e2) {
            }
            return false;
        }

    }

    //https://www.spooncast.net/kr/live/27868843
    public static String getText() {
        try {
            return ElementId.MESSAGE.getElement().getText();
        } catch (Exception e) {
            try {
                List<WebElement> list = driver.findElements(By.xpath(
                        "/html/body/div[1]/div/main/div/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[*]/div/div/div/div/pre"));
                return list.get(list.size() - 1).getText();
            } catch (Exception e2) {
                return "null";
            }
        }
    }

    public static String getLikeUser() {
        try {
            String name = ElementId.LIKE.getElement().getText();
            if(name.contains("님이 좋아요를 누르셨습니다."))
                return name.replace("님이 좋아요를 누르셨습니다.", "");
        } catch (Exception e) {
        }
        return lastLikeUser;

    }

    private static String lastMessageUser = "";

    public static String getName() {
        try {
            String name = ElementId.USER_NAME.getElement().getText();
            if (name != null) lastMessageUser = name;
        } catch (Exception e) {
        }
        return lastMessageUser;

    }

    public static void sendMessage(String message) {
        if (message.equals(""))
            return;
        try {
            WebElement webElement = driver.findElement(By.xpath(Properties.data.get("SendMessageKey")));
            webElement.sendKeys(message.replaceAll("%n%", "\r").replaceAll("\\r", "\r"));
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            try {
                WebElement webElement = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div[1]/div/div[3]/div[4]/div[2]/textarea"));
                webElement.sendKeys(message.replaceAll("%n%", "\r").replaceAll("\\r", "\r"));
                webElement.sendKeys(Keys.ENTER);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void start() {
        if (commands == null)
            commands = new HashMap<String, String>();
        if (driver != null) {
            System.out.println("start error");
            return;
        }
        String viewURL = Main.view.url.getText();
        url = viewURL;
        if (joinMessage == null) {
            joinMessage = "";
        }
        if (spoonMessage == null) {
            spoonMessage = "";
        }
        spoon = 0;
        lastMessage = "null";
        lastLikeUser = "";
        playList = new ArrayList<String>();

        run();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
