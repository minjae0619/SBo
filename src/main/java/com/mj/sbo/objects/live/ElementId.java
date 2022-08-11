package com.mj.sbo.objects.live;

import com.mj.sbo.objects.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public enum ElementId{
//    #root > div > main > div > div > div.live-detail-contents > div > div.live-detail-chat-container.listener > div.live-detail-comment-list >" +
//            " div > div:nth-child(1) > ul > li:nth-last-child(1) > div > div > div > p
//#root > div > main > div > div > div.live-detail-contents > div > div.live-detail-chat-container.dj > div.live-detail-comment-list >
// div > div:nth-child(1) > ul > li.chat-list-item.like > p

    LIKE("#root > div > main > div > div > div.live-detail-contents > div > div.live-detail-chat-container.listener > " +
            "div.live-detail-comment-list > div > div:nth-child(1) > ul > li:nth-last-child(1)",
            ""),
    ON_AIR("#root > div > main > div > div.profile-contents > div.profile-info-container.has-voice-profile > div.profile-info-wrap.on-live > div.profile-info > div.btn-live-badge-wrap > a", ""),
    USER_NAME(
            "#root > div > main > div > div > div.live-detail-contents > div > div.live-detail-chat-container.listener >" +
                    " div.live-detail-comment-list > div > div:nth-child(1) > ul > li:nth-last-child(1) > div > div > div > p > button",
            ""),
    MESSAGE("#root > div > main > div > div > div.live-detail-contents > div > div.live-detail-chat-container.listener > " +
                    "div.live-detail-comment-list > div > div:nth-child(1) > ul > li:nth-last-child(1) > div > div > div > div > pre",
            ""),

    RELOAD_USERS("", ""),
    TIME("", ""),
    SPOON("", ""),
    USER_SIZE("", "");

    private String normal;
    private String liveCall;
    ElementId(String normal, String liveCall) {
        this.normal = normal;
        this.liveCall = liveCall.equals("") ? normal : liveCall;
    }

    public String getLiveCall() {
        return liveCall;

    }

    public WebElement getElement(){
        return Bot.driver.findElement(By.cssSelector(getLiveCall()));
    }

    public String getNormal() {
        return normal;
    }
}
