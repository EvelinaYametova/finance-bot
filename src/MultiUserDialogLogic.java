import java.util.HashMap;
import java.util.Map;

public class MultiUserDialogLogic {
    private Map<Long, DialogLogic> chatIdLogic = new HashMap<Long, DialogLogic>();

    public String getOutText(long chatId, String inText){
        if (inText.equals("/start")){
            return "Привет! Для вызова справки введи команду /help";
        }
        else {
            if (!chatIdLogic.containsKey(chatId)) {
                chatIdLogic.put(chatId, new DialogLogic());
            }
            return this.chatIdLogic.get(chatId).executeCommand(inText);
        }
    }
}
