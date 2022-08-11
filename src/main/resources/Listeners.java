import com.mj.sbo.event.*;
import com.mj.sbo.event.handler.*;
import com.mj.sbo.utils.live.Guide;
import com.mj.sbo.utils.sound.*;

/*
-저장 단축키 = ctrl + s

-기타
playDonation("텍스트"); = 봇이 "텍스트"를 대신 읽어줍니다.
playSound("파일 경로"); = 해당 경로에 있는 파일의 소리를 재생합니다.
sendMessage("안녕하세요"); = 스푼 채팅창에 안녕하세요라는 메세지를 출력합니다.
sendLog("안녕하세요"); = 로그창에 안녕하세요라는 메세지를 출력합니다.

-룰렛
Roulette.getInstance().add("1번", 50); // 룰렛에 1번이라는 항목을 추가합니다 (50%확률)
Roulette.getInstance().add("2번", 30); // 룰렛에 2번이라는 항목을 추가합니다 (30%확률)
Roulette.getInstance().add("3번", 20); // 룰렛에 3번이라는 항목을 추가합니다 (20%확률)
Roulette.getInstance().getRandomMessage(); = 설정해둔 룰렛 메세지 중 하나가 랜덤으로 반환됩니다.
확률의 총 합이 100이 되어야합니다 (초과 x)

-코드 예시
    @EventHandler
    public void onCommand(CommandEvent event){
        if(event.getCommand().equals("!test")){ //만약 입력한 명령어가 !test일 때
            sendMessage("테스트 메세지"); //테스트 메세지를 출력한다.
        }

    }
 */
public class Listeners extends Guide implements Listener {

    static{
        //load될 때 실행됩니다.
        //기본값 설정은 여기서 해주면 됩니다. ( ex) 룰렛 )

    }
    public void debug(){
        //UI의 디버그 메세지 클릭 시 실행되는 메소드(함수)
    }

    @EventHandler
    public void onCommand(CommandEvent event){
//      event.getCommand(); = 입력한 명령어
//      event.getUserName(); = 해당 유저의 이름
    }

    @EventHandler
    public void onChat(ChatEvent event){
//      event.getMessage(); = 입력한 메세지
//      event.getUserName(); = 해당 유저의 이름
    }

    @EventHandler
    public void onJoin(JoinEvent event){
//      event.getUserName(); = 입장한 유저의 이름
//        event.setJoinMessage("입장"); = 입장 메세지
    }

    @EventHandler
    public void onSpoon(SpoonEvent event){
//      event.getSpoon() = 스푼의 갯수
//      event.setSpoonMessage() = 메세지
//      event.getUserName() = 해당 유저의 이름
    }

    @EventHandler
    public void onLike(LikeEvent event){
//      event.getUserName() = 해당 유저의 이름
    }
}
