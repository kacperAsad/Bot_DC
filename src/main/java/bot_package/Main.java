package bot_package;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) {

        String token = "NzUwNzc2MDA5Mzg4ODUxMzQx.X0_cpQ.fiYW7ponXilo698Adu7ouxf6jyk";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.addMessageCreateListener(messageCreateEvent -> {
            if (messageCreateEvent.getMessageAuthor().isBotUser())return;
           if (messageCreateEvent.getMessageContent().equalsIgnoreCase("Hello"))
           {
               messageCreateEvent.getChannel().sendMessage("World");
           }
        });
    }
}
