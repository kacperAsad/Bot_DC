package bot_package;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;


public class Main {

    public final static String prefix = ".";
    public static boolean debug_on_channel = true;
    public static boolean messaging = true;

    public static void main(String[] args) {

        String token = args[0];

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Listener listener = new Listener(Listener.Listener_types.all);
        api.addMessageCreateListener(listener);
        api.addMessageDeleteListener(listener);
        api.addMessageEditListener(listener);


        api.addMessageCreateListener(messageCreateEvent -> {
            if (messageCreateEvent.getMessageAuthor().isBotUser())return;
           if (messageCreateEvent.getMessageContent().equalsIgnoreCase("Hello"))
           {
               messageCreateEvent.getChannel().sendMessage("World");

           }
        });
        System.out.println(api.createBotInvite());
    }
}
