import java.util.HashMap;
import java.util.Map;

public class BotReply{
    Map<String, String> commandStorage = new HashMap<String, String>();
    public BotReply() {
        commandStorage.put("/start", "Привет, я помогу тебе контролировать твои расходы. Чтобы получить больше информации, введи /help");
        commandStorage.put("/help", "Я твой финансовый бот. Вот что я могу:\n" +
                "/help - вызвать справку\n" +
                "/data - показать данные о твоих финансах на текущий момент\n" +
                "/exit - выйти из диалога\n" +
                "/change - изменить данные о финансах");
        commandStorage.put("/data", "Твои данные о финансах на текущий момент:\n");
        commandStorage.put("/else", "Все доступные команды ты можешь посмотреть, введя команду /help");
        commandStorage.put("/exit", "До скорых встреч!");
        commandStorage.put("/change", "Вы хотите изменить свои данные? (Да/Нет)");
    }

    private AccountData data = new AccountData(0,0,0);
    private State state = State.INITIAL;

    private String getAnswerForChange(String line){
        if (line.equalsIgnoreCase("Нет")){
            state = State.INITIAL;
            return "";
        }
        if (line.equalsIgnoreCase("Да")) {
            state = State.CHANGE_EXPENSES;
            return "Введите свои расходы";
        }
        else {
            return "Ответом на данный вопрос может быть Да или Нет";
        }
    }

    private String changeAccountData(String line){
        double money;
        try {
            money = Double.parseDouble(line);
        }
        catch(Exception e) {
            return "Ошибка! Введите число >= 0";
        }

        if (state == State.CHANGE_EXPENSES && money >= 0) {
            this.data.MyExpense += money;
            this.data.MyTotal -= money;
            state = State.CHANGE_INCOME;
            return "Введите свои доходы";
        }
        else if (state == State.CHANGE_INCOME && money >= 0){
            this.data.MyIncome += money;
            this.data.MyTotal += money;
            state = State.INITIAL;
            return "Выполнено!";
        }
        else{
            return "Ошибка! Введите число >= 0";
        }
    }

    enum State{
        INITIAL,
        CHANGE_CONFIRMATION,
        CHANGE_EXPENSES,
        CHANGE_INCOME
    }

    public String generateAnswerByLine(String line) {
        if (state == State.CHANGE_CONFIRMATION) {
            return getAnswerForChange(line);
        }

        if (state == State.CHANGE_EXPENSES || state == State.CHANGE_INCOME) {
            return changeAccountData(line);
        }

        StringBuilder answer = new StringBuilder(commandStorage.get(line));

        switch (line) {
            case "/data":
                answer.append(data.toString());
                break;
            case "/change":
                state = State.CHANGE_CONFIRMATION;

        }
        return answer.toString();
    }
}