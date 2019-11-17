import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class DialogTest {

    @org.junit.jupiter.api.Test
    void executeCommand() {
        String expected = "Я твой финансовый бот. Вот что я могу:\n" +
                "/help - вызвать справку\n" +
                "/data - показать данные о твоих финансах на текущий момент\n" +
                "/exit - выйти из диалога\n" +
                "/change - изменить данные о финансах";
        DialogLogic dialog = new DialogLogic();
        String actual = dialog.executeCommand("/help");
        Assert.assertEquals(expected, actual);
    }
}