import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class DialogLogic {
    private AccountData data = new AccountData(0,0);
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
            state = State.CHANGE_INCOME;
            return "Введите свои доходы";
        }
        else if (state == State.CHANGE_INCOME && money >= 0){
            this.data.MyIncome += money;
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

    public String executeCommand(String line) {
        if (state == State.CHANGE_CONFIRMATION) {
            return getAnswerForChange(line);
        }

        if (state == State.CHANGE_EXPENSES || state == State.CHANGE_INCOME) {
            return changeAccountData(line);
        }

        StringBuilder answer = new StringBuilder(CommandStorage.get(line).getText());
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
