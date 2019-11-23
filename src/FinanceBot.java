import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class FinanceBot extends TelegramLongPollingBot {

    public FinanceBot() throws IOException {
    }

    public static void main(String[] args) {
        System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "127.0.0.1" );
        System.getProperties().put( "socksProxyPort", "9150" );
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new FinanceBot());
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }

   public String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader((fileName)));
        String line = null;
        StringBuilder out = new StringBuilder();
        while((line = reader.readLine()) != null) {
            out.append(line);
            }
        return out.toString();
    }
    private String token = readFile("src/main/bot-token.txt");
    @Override
    public String getBotToken() {
        return token;
    }

    private MultiUserDialogLogic mudLogic = new MultiUserDialogLogic();
    /**
     * Метод-обработчик поступающих сообщений.
     * @param update объект, содержащий информацию о входящем сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();//Извлекаем объект входящего сообщения
                long chatId = inMessage.getChatId();
                String inText = inMessage.getText();
                SendMessage outMessage = new SendMessage();//Создаем исходящее сообщение
                outMessage.setChatId(chatId);//Указываем в какой чат будем отправлять сообщение (в тот же чат, откуда пришло входящее сообщение)
                outMessage.setText(mudLogic.getOutText(chatId, inText));
                execute(outMessage);//Отправляем сообщение
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "FinanceDataBot";
    }
}
