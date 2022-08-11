package com.mj.sbo.listeners;

import com.mj.sbo.objects.Bot;
import com.mj.sbo.Main;
import com.mj.sbo.event.CommandEvent;
import com.mj.sbo.event.handler.*;
import com.mj.sbo.objects.Roulette;


public class CommandListener implements Listener {

    @EventHandler
    public void onPlayList(CommandEvent event){
        String text = event.getCommand();
        String name = event.getUserName();
        if(!(text.startsWith("!ps")||text.startsWith("!pa")||text.startsWith("!next") ||
                text.startsWith("!n") ||text.startsWith("!pr")||text.startsWith("!신청곡")||
                text.startsWith("!pl")||text.startsWith("!플레이리스트") || text.startsWith("!플리")||text.startsWith("!현재곡"))) {
            return;
        }
        if(text.startsWith("!next") || text.startsWith("!n")) {
            if(!(name.equalsIgnoreCase(Main.ADMIN)||Bot.isManager())){
                Bot.sendMessage("권한이 없습니다.");
                return;
            }
            try {
                Bot.sendMessage("다음 곡으로 넘어갑니다.");
                Bot.playList.remove(0);
                return;
            } catch (Exception e) {
            }
        }else if(text.startsWith("!pr")) {
            if(!(name.equalsIgnoreCase(Bot.developerName)||Bot.isManager())){
                Bot.sendMessage("권한이 없습니다.");
                return;
            }
            String[] arr = text.split(" ");
            if(arr.length > 1) {
                try {
                    int a = Integer.parseInt(arr[1])-1;
                    Bot.sendMessage(Bot.playList.remove(a) + "(을)를 제거하셨습니다.");
                    return;
                } catch (Exception e) {
                    Bot.sendMessage("- error - %n% !pr [번호]");
                }
            }
        }else if(text.startsWith("!ps")) {
            if(!(name.equalsIgnoreCase(Bot.developerName)||Bot.isManager())){
                Bot.sendMessage("권한이 없습니다.");
                return;
            }
            String[] arr = text.split(" ");
            String str = "";
            for(int a = 2; a < arr.length; a++) {
                str += arr[a] + " ";
            }
            if(arr.length > 2) {
                try {
                    int a = Integer.parseInt(arr[1])-1;
                    Bot.playList.set(a, str);
                    Bot.sendMessage((a+1) + "번을 " + str +"으로 설정하셨습니다.");
                    return;
                } catch (Exception e) {
                    Bot.sendMessage("- error - %n%!ps [번호] [가수+노래제목]");
                }
            }else {
                Bot.sendMessage("- !ps [번호] [가수+노래제목]");
            }
        }else if(text.startsWith("!pa")) {
            if(!(name.equalsIgnoreCase(Bot.developerName)||Bot.isManager())){
                Bot.sendMessage("권한이 없습니다.");
                return;
            }
            String[] arr = text.split(" ");
            String str = "";
            for(int a = 2; a < arr.length; a++) {
                str += arr[a] + " ";
            }
            if(arr.length > 2) {
                try {
                    int a = Integer.parseInt(arr[1])-1;
                    Bot.playList.add(a, str);
                    Bot.sendMessage(str + "(을)를 " + (a+1) + "번에 추가하셨습니다.");
                    return;
                } catch (Exception e) {
                    Bot.sendMessage("- error - %n%!pa [번호] [가수+노래제목]");
                }
            }else {
                Bot.sendMessage("- !pa [번호] [가수+노래제목]");
            }
        }else if(text.startsWith("!신청곡")) {
            String[] arr = text.split(" ");
            if(arr.length > 1) {
                String str = "";
                for(int a = 1; a < arr.length; a++) {
                    str += arr[a] + " ";
                }
                Bot.playList.add(str);
                Bot.sendMessage(str + "(을)를 신청하셨습니다.");
                return;
            }

        }else if(text.startsWith("!현재곡")) {
            if(Bot.playList.size() < 1) {
                Bot.sendMessage("🎶 플레이리스트가 비어있습니다 🎶");
                return;
            }
            Bot.sendMessage("🎶현재곡 : " + Bot.playList.get(0));
            return;
        }else if(text.startsWith("!플레이리스트") || text.startsWith("!플리") ||text.startsWith("!pl")) {
            int num = 1;
            String str = "";
            if(Bot.playList.size() < 1) {
                Bot.sendMessage("🎶 플레이리스트가 비어있습니다 🎶");
                return;
            }
            int page = 1;
            String[] arr = text.split(" ");
            if(arr.length == 1) {
                for(String s : Bot.playList) {
                    if(num == 4)
                        break;
                    str += num + ". " + s + "\r";
                    num++;
                }
            }else {
                try {
                    page = Integer.parseInt(arr[1]);
                } catch (Exception e) {
                }
                for(int a = (3*page)-3; a < Bot.playList.size(); a++) {
                    if(num == 4)
                        break;
                    if(Bot.playList.size() <= a)
                        break;
                    str += (a+1) + ". " + Bot.playList.get(a) + "\r";
                    num++;
                }

            }
            Bot.sendMessage(str + "PlayList ("+page+"/"+((int)Math.ceil(Bot.playList.size()/3.0))+ ")");
        }

        return;
    }



    @EventHandler
    public void onBasicCommand(CommandEvent event){
        String command = event.getCommand();
        if(command.startsWith("!추첨") ) {
            int random = (int) (Math.random() * Bot.users.size()-1);
            Bot.sendMessage(Bot.users.get(random));
            return;
        }else if(command.startsWith("!룰렛") ) {
            Bot.sendMessage(Roulette.getInstance().getRandomMessage());
            return;
        }
    }

}
