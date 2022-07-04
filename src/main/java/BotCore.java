import Oracle.Documentation;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCore extends TelegramLongPollingBot {

    Documentation documentation = new Documentation();

    private static final String START = "start";
    private static final String BOTINFO = "botinfo";

    @Override
    public void onUpdateReceived(Update update) {

        // TODO writing logic...

        if (update.hasMessage() && update.getMessage().hasText()) {

            //search message in Map
            if (documentation.classDoc.containsKey(update.getMessage().getText())){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText(documentation.classDoc.get(update.getMessage().getText()));
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            //buttons
            switch (update.getMessage().getText()) {

                case START -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("Hello World");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case BOTINFO -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("Справка Java");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "JavaDocsOracleBot";
    }

    @Override
    public String getBotToken() {
        //TODO Token
        return "5563986239:AAEx34sb_SmhOrEc_T4OhVC0hmxrqtZE1iQ";
    }
}
