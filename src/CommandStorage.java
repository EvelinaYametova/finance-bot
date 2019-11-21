import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum CommandStorage {
    START("/start", "Привет, я помогу тебе контролировать твои расходы. Чтобы получить больше информации, введи /help", "/start - начать работу с ботом\n"),
    HELP("/help", "Я твой финансовый бот. Вот что я могу:\n", "/help - вызвать справку\n" ),
    DATA("/data", "Твои данные о финансах на текущий момент:\n", "/data - показать данные о твоих финансах на текущий момент\n"),
    ELSE("/else", "Все доступные команды ты можешь посмотреть, введя команду /help", ""),
    CHANGE("/change", "Вы хотите изменить свои данные? (Да/Нет)", "/change - изменить данные о финансах\n"),
    EXIT("/exit", "До скорых встреч!","/exit - выйти из диалога\n");

    private String key;
    private String text;
    private String helpText;
    public String getKey(){ return key;}
    private static final Map<String, CommandStorage> KEY_TO_ENUM;
    static{
        Map<String, CommandStorage> key_to_enum = new HashMap<String, CommandStorage>();
        for (CommandStorage com : CommandStorage.values()) {
            key_to_enum.put(com.getKey(), com);
        }
        KEY_TO_ENUM = Collections.unmodifiableMap(key_to_enum);
    }

    CommandStorage(String key, String text, String helpText)
    {
        this.key = key;
        this.text = text;
        this.helpText = helpText;
    }

    public String getText() {
        String answer = text;
        if (this == HELP) {
            answer += CommandStorage.getHelp();
        }
        return answer;
    }

    public String getHelpText(){ return helpText;}
    public static String getHelp(){
        String help = "";
        for (CommandStorage com : CommandStorage.values()) {
            help += com.getHelpText();
        }
        return help;
    }

    public static CommandStorage get(String test) {
        for (CommandStorage com : CommandStorage.values()) {
            if (com.getKey().equals(test)) {
                return com;
            }
        }
        return CommandStorage.ELSE;
    }
}
